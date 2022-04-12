package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demo.service.ColorService;

@RestController
public class ColorController {

    @Autowired
    private ColorService colorService;
    
    @GetMapping("/random-color")
    public ResponseEntity<?> randomColor(@RequestParam int type){
        String color = colorService.randomColor(type);
        return ResponseEntity.ok(color);
    }
}

//Tìm hiểu ResponseEntity
