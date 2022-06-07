package vn.techmaster.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.banking.model.Account;
import vn.techmaster.banking.model.User;
import vn.techmaster.banking.repositoy.AccountRepo;
import vn.techmaster.banking.repositoy.UserRepo;
import vn.techmaster.banking.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired private AccountRepo accountRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private AccountService accountService;
    @GetMapping
    public ResponseEntity<List<Account>> getAllAcount() {
        return ResponseEntity.ok().body(accountRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAcountByID(@PathVariable("id") String id){
        return ResponseEntity.ok().body(accountRepo.findById(id).get());
    }

    @GetMapping("findbyuserid/{id}")
    public ResponseEntity<?> findAcountByUserID(@PathVariable("id") String id){
        User user = userRepo.findById(id).get();
        return ResponseEntity.ok().body(accountRepo.findAllByUser(user));
    }

@GetMapping("transfer")
    public ResponseEntity<?> tranfer(@RequestParam("senderid") String accSend , @RequestParam("receiverid") String accRecv, @RequestParam long amount){
        accountService.transfer(accSend,accRecv,amount);
        return  ResponseEntity.ok("Chuyển tiền thành công");
    }
}
