package vn.techmaster.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.bank.model.SavingAccount;

public interface SavingAccountRepo extends JpaRepository<SavingAccount,String> {
    
}
