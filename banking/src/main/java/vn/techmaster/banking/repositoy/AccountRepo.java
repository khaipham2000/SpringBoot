package vn.techmaster.banking.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.banking.model.Account;
import vn.techmaster.banking.model.User;

@Repository
public interface AccountRepo extends JpaRepository<Account, String>{
    List<Account> findAllByUser(User user);
}
