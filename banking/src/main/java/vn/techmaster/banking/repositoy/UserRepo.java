package vn.techmaster.banking.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.banking.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
  
}
