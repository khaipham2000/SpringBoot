package vn.techmaster.demo.service;

import org.springframework.stereotype.Service;
import vn.techmaster.demo.exception.BadRequestException;

@Service
public class BmiService {
    public double caculateBmi(double height , double weight){
        if(height <= 0 || weight <= 0){
            throw new BadRequestException("Chiều cao hoặc cân nặng không hợp lệ");
        }
        return weight/(height * height);
    }
}
