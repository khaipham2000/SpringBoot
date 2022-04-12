package vn.techmaster.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.Request.BmiRequest;
import vn.techmaster.demo.service.BmiService;

@RestController
@AllArgsConstructor
public class BmiController {
    private BmiService bmiService;
    @GetMapping("/bmi")
    public ResponseEntity<?> caculateBmiGet(@RequestParam double height , @RequestParam double weight){
        double bmi = bmiService.caculateBmi(height,weight);
        return ResponseEntity.ok(bmi);
    }

    @PostMapping("/bmi")
    public ResponseEntity<?> caculateBmiPost(@RequestBody BmiRequest request){
        double bmi = bmiService.caculateBmi(request.getHeight(),request.getWeight());
        return ResponseEntity.ok(bmi);
    }
}
