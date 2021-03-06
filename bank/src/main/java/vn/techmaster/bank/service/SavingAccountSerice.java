package vn.techmaster.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.bank.exception.CommandException;
import vn.techmaster.bank.exception.RecordNotFoundException;
import vn.techmaster.bank.model.Account;
import vn.techmaster.bank.model.SavingAccount;
import vn.techmaster.bank.model.SavingType;
import vn.techmaster.bank.model.User;
import vn.techmaster.bank.repository.AccountRepo;
import vn.techmaster.bank.repository.SavingAccountRepo;
import vn.techmaster.bank.repository.UserRepo;
import vn.techmaster.bank.request.SavingAccountRequest;
import vn.techmaster.bank.request.WithDrawSaveRequest;
import vn.techmaster.bank.response.SavingAccountInfo;

@Service
public class SavingAccountSerice {
    @Autowired private UserRepo userRepo;
    @Autowired private AccountRepo accountRepo;
    @Autowired private SavingAccountRepo accountSaverRepo;
    @Autowired private RateService rateService;

    public SavingAccountInfo openAccount(SavingAccountRequest accountSaverRequest){
        User user = userRepo.findById(accountSaverRequest.userId())
        .orElseThrow(() -> new RecordNotFoundException("users", accountSaverRequest.userId()));

        Account account = accountRepo.findById(accountSaverRequest.accountId())
        .orElseThrow(() -> new RecordNotFoundException("account", accountSaverRequest.accountId()));

        if(accountSaverRequest.amount() > account.getBalance()){
            throw new CommandException("Not Enough Balance To Transfer");
        }
        
        if(rateService.findRateByMonth(accountSaverRequest.months()) == null){
            throw new CommandException("This month is not valid");
        }

        account.setBalance(account.getBalance()-accountSaverRequest.amount());
        SavingAccount newSavingAcc;
        if(accountSaverRequest.typeSave().toString().equals("FINAL")){
            newSavingAcc = SavingAccount.builder()
        .id(UUID.randomUUID().toString())
        .account(account)
        .startBalance(accountSaverRequest.amount())
        .endBalance(accountSaverRequest.amount())
        .months(accountSaverRequest.months())
        .rate(RateService.findRateByMonth(accountSaverRequest.months()))
        .typeSave(accountSaverRequest.typeSave())
        .openAt(LocalDateTime.now())
        .updateAt(null)
        .closeAt(LocalDateTime.now().plusMonths(accountSaverRequest.months())).build();
        }
        newSavingAcc = SavingAccount.builder()
        .id(UUID.randomUUID().toString())
        .account(account)
        .startBalance(accountSaverRequest.amount())
        .endBalance(accountSaverRequest.amount())
        .months(accountSaverRequest.months())
        .rate(rateService.findRateByMonth(accountSaverRequest.months()))
        .typeSave(accountSaverRequest.typeSave())
        .openAt(LocalDateTime.now())
        .updateAt(LocalDateTime.now().plusMonths(1))
        .closeAt(LocalDateTime.now().plusMonths(accountSaverRequest.months())).build();
        accountSaverRepo.save(newSavingAcc);

        return new SavingAccountInfo(newSavingAcc.getId(), 
        newSavingAcc.getStartBalance(), 
        newSavingAcc.getMonths(), 
        newSavingAcc.getRate(), 
        newSavingAcc.getOpenAt(), newSavingAcc.getCloseAt());
    }

    public String withDrawSaveAccount(WithDrawSaveRequest withDrawSaveRequest){
        User user = userRepo.findById(withDrawSaveRequest.userID())
    .orElseThrow(() -> new RecordNotFoundException("users", withDrawSaveRequest.userID()));

        Account account = accountRepo.findById(withDrawSaveRequest.accountID())
    .orElseThrow(() -> new RecordNotFoundException("account", withDrawSaveRequest.accountID()));

       SavingAccount accountSaving = accountSaverRepo.findById(withDrawSaveRequest.accountSaverID())
       .orElseThrow(() -> new RecordNotFoundException("accountsaver", withDrawSaveRequest.accountSaverID()));
        if(accountSaving.getTypeSave().equals(SavingType.FINAL)){
            Double bonusBalance;
            if(accountSaving.getMonths() == 0){
                bonusBalance= accountSaving.getStartBalance()*((accountSaving.getRate()/100)/12);
            } else {
                bonusBalance = accountSaving.getStartBalance()*accountSaving.getMonths()*((accountSaving.getRate()/100)/12);
            }
            if(accountSaving.getMonths() != 0 && accountSaving.getCloseAt().compareTo(LocalDateTime.now()) > 0){
                throw new CommandException("Ch??a ?????n h???n r??t l??i");
            }
           
            account.setBalance(account.getBalance()+bonusBalance);
            accountRepo.save(account);
            accountSaving.setStartBalance(0D);
            accountSaverRepo.deleteById(accountSaving.getId());
    
            return "T??i kho???n "+ account.getId() + " nh???n th??nh c??ng kho???n l??i: "+ bonusBalance;
        }
        final Double currentBalance = accountSaving.getStartBalance();
        Double bonusBalancePerMonth = currentBalance*(((accountSaving.getRate()*0.8)/100)/12);
        if(accountSaving.getUpdateAt().compareTo(accountSaving.getCloseAt()) < 0){
            if(accountSaving.getUpdateAt().compareTo(LocalDateTime.now()) > 0){
                throw new CommandException("Ch??a ?????n h???n r??t l??i");
            } else {
            accountSaving.setEndBalance((accountSaving.getEndBalance()+bonusBalancePerMonth));
            accountSaving.setUpdateAt(accountSaving.getUpdateAt().plusMonths(1));
            accountSaverRepo.save(accountSaving);
            return "T??i kho???n ti???t ki???m "+ accountSaving.getId() + " nh???n th??nh c??ng kho???n l??i h??ng th??ng: "+ bonusBalancePerMonth;
            }
            
        } else {
            account.setBalance(account.getBalance()+accountSaving.getEndBalance()+bonusBalancePerMonth);
            accountRepo.save(account);
            accountSaverRepo.deleteById(accountSaving.getId());
            return "T??i kho???n "+ account.getId() + " nh???n th??nh c??ng kho???n g???c k??m l??i: "+ (accountSaving.getEndBalance()+bonusBalancePerMonth) + " v???i s??? ti???n l??i: "+
            currentBalance*accountSaving.getMonths()*(((accountSaving.getRate()*0.8)/100)/12);
        }

        
    }

    public SavingAccount findAccSavebyAccID(String accountID){
        List<SavingAccount> listAccSave = accountSaverRepo.findAll();
        SavingAccount finalAccSave = new SavingAccount();
        for (int i = 0; i < listAccSave.size(); i++) {
            if(listAccSave.get(i).getAccount().getId().equalsIgnoreCase(accountID)){
                finalAccSave = listAccSave.get(i);
            }
            else {
                throw new CommandException("Account is not found");
            }
        }
        return finalAccSave;
    }
    }

