package vn.techmaster.bank.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.bank.exception.CommandException;
import vn.techmaster.bank.exception.RecordNotFoundException;
import vn.techmaster.bank.model.Account;
import vn.techmaster.bank.model.CommandStatus;
import vn.techmaster.bank.model.Deposit;
import vn.techmaster.bank.model.Transfer;
import vn.techmaster.bank.model.User;
import vn.techmaster.bank.model.Withdraw;
import vn.techmaster.bank.repository.AccountRepo;
import vn.techmaster.bank.repository.CommandRepo;
import vn.techmaster.bank.repository.UserRepo;
import vn.techmaster.bank.request.DepositRequest;
import vn.techmaster.bank.request.TransferRequest;
import vn.techmaster.bank.request.WithdrawRequest;
import vn.techmaster.bank.response.AccountInfo;

@Service
public class BankService {
  @Autowired private UserRepo userRepo;
  @Autowired private AccountRepo accountRepo;
  @Autowired private CommandRepo commandRepo;
  private TransferRequest transferRequest;
  @Transactional
  public AccountInfo deposit(DepositRequest depositRequest) {
    User user = userRepo.findById(depositRequest.userId())
    .orElseThrow(() -> new RecordNotFoundException("users", depositRequest.userId()));

    Account account = accountRepo.findById(depositRequest.accountId())
    .orElseThrow(() -> new RecordNotFoundException("account",depositRequest.accountId()));

    if (!account.getUser().getId().equals(depositRequest.userId())) {
      throw new CommandException("Requester is not owner of account");
    }

    account.setBalance(account.getBalance() + depositRequest.amount());
    Deposit deposit = new Deposit (user, account, depositRequest.amount());
    try {
      accountRepo.save(account);
      deposit.setStatus(CommandStatus.SUCCESS);
      commandRepo.save(deposit);
      return new AccountInfo(account.getId(), account.getBank().getName(), account.getBalance());
    } catch (Exception ex) {
      deposit.setStatus(CommandStatus.FAILED);
      commandRepo.save(deposit);
      var commandException = new CommandException("Deposit error");
      commandException.initCause(ex);
      throw commandException;
    }
  }

  public AccountInfo withdraw(WithdrawRequest withdrawRequest) {
    User user = userRepo.findById(withdrawRequest.userId())
    .orElseThrow(() -> new RecordNotFoundException("users", withdrawRequest.userId()));

    Account account = accountRepo.findById(withdrawRequest.accountId())
    .orElseThrow(() -> new RecordNotFoundException("account",withdrawRequest.accountId()));

    if (!account.getUser().getId().equals(withdrawRequest.userId())) {
      throw new CommandException("Requester is not owner of account");
    }

    if (account.getBalance() > withdrawRequest.amount()) {
      account.setBalance(account.getBalance() - withdrawRequest.amount());
    } else {
      throw new CommandException("Not enough money");
    }
    
    Withdraw withdraw = new Withdraw (user, account, withdrawRequest.amount());
    try {
      accountRepo.save(account);
      withdraw.setStatus(CommandStatus.SUCCESS);
      commandRepo.save(withdraw);
      return new AccountInfo(account.getId(), account.getBank().getName(), account.getBalance());
    } catch (Exception ex) {
      withdraw.setStatus(CommandStatus.FAILED);
      commandRepo.save(withdraw);
      var commandException = new CommandException("Deposit error");
      commandException.initCause(ex);
      throw commandException;
    }
  }

  public Map<String,AccountInfo> transfer(TransferRequest transferRequest){
    this.transferRequest = transferRequest;
    User user = userRepo.findById(transferRequest.userId())
    .orElseThrow(() -> new RecordNotFoundException("users", transferRequest.userId()));

    Account sendAcc = accountRepo.findById(transferRequest.sendAccId())
    .orElseThrow(() -> new RecordNotFoundException("account", transferRequest.sendAccId()));

    Account revAcc = accountRepo.findById(transferRequest.receiveAccId())
    .orElseThrow(() -> new RecordNotFoundException("account", transferRequest.receiveAccId()));

    if(!sendAcc.getUser().getId().equals(transferRequest.userId())){
        throw new CommandException("Owner of Account is not User");
    }
    if(transferRequest.amount() > sendAcc.getBalance()){
        throw new CommandException("Not Enough Balance To Transfer");
    }

    sendAcc.setBalance(sendAcc.getBalance() - transferRequest.amount());
    revAcc.setBalance(revAcc.getBalance() + transferRequest.amount() );
    Transfer transfer = new Transfer(user,sendAcc,revAcc,transferRequest.amount());
    try {
        accountRepo.save(sendAcc);
        accountRepo.save(revAcc);
        transfer.setStatus(CommandStatus.SUCCESS);
        commandRepo.save(transfer);
        Map newTransfer = new HashMap<>();
        newTransfer.put("Chuyển thành công số tiền "+ transferRequest.amount() 
        + " tới tài khoản " + revAcc.getId() 
        +" tại ngân hàng "+revAcc.getBank().getName(),
        new AccountInfo(sendAcc.getId(), sendAcc.getBank().getName(), sendAcc.getBalance()));
        return newTransfer;
    } catch (Exception ex) {
        transfer.setStatus(CommandStatus.FAILED);
        commandRepo.save(transfer);
        var commandException = new CommandException("Transfer error");
        commandException.initCause(ex);
        throw commandException;
    }
}


}
