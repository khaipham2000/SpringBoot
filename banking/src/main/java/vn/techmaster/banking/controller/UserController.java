package vn.techmaster.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.banking.model.User;
import vn.techmaster.banking.repositoy.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired private UserRepo userRepo;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserByID(@PathVariable("id") String id){
        return ResponseEntity.ok().body(userRepo.findById(id).get());
    }
    
}
