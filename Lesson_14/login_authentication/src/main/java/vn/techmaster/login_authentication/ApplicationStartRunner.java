package vn.techmaster.login_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import vn.techmaster.login_authentication.service.UserService;

public class ApplicationStartRunner implements ApplicationRunner {
   @Autowired UserService userService;
   @Override
   public void run(ApplicationArguments args) throws Exception{
       userService.addUserThenAutoActivate("admin", "moimoi@gmail.com", "abc123");
       userService.addUser("adminmoi", "moi2k5@gmail.com", "ab0123");
   } 
}
