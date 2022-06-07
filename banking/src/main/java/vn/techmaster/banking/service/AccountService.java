package vn.techmaster.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.banking.exception.NotFoundException;
import vn.techmaster.banking.exception.TransferException;
import vn.techmaster.banking.model.Account;
import vn.techmaster.banking.repositoy.AccountRepo;

@Service
public class AccountService {
    @Autowired private AccountRepo accountRepo;

    public void transfer(String senderAccId,String receiverAccId, long amount){
        Optional<Account> accountSendOptional = accountRepo.findById(senderAccId);
        Account accountSend = new Account();
        if(accountSendOptional.isPresent()){
            accountSend  = accountSendOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + senderAccId + " not found");
        }
        Account accountReceive = new Account();
        Optional<Account> accountRecvOptional = accountRepo.findById(receiverAccId);
        if(accountRecvOptional.isPresent()){
            accountReceive = accountRecvOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + receiverAccId+ " not found");
        }

        long moneySend = accountSend.getBalance();
        long moneyReceive = accountReceive.getBalance();

        if(moneySend < 0){
            throw new TransferException("Deposit must be greater than 0");
        }
        if(moneySend < amount){
            throw new TransferException("The balance is not enough to make transaction");
        }
        if(amount < 0){
            throw new TransferException("Sender account is empty");
        }
        accountSend.setBalance(moneySend - amount);
        accountReceive.setBalance(moneyReceive + amount);
        accountRepo.save(accountSend);
        accountRepo.save(accountReceive);
}
}
