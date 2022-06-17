package vn.techmaster.bank.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.bank.model.Rate;
import vn.techmaster.bank.repository.RateRepo;

@Service
public class RateService {
    @Autowired
    static RateRepo rateRepo;

    public static ConcurrentHashMap<Long,Double> getAllRate(){
        List<Rate> allRate = rateRepo.findAll();
        ConcurrentHashMap<Long,Double> newMapRate = new ConcurrentHashMap<>();
        for(int i = 0 ; i <allRate.size();i++){
            newMapRate.put(allRate.get(i).getMonths(),allRate.get(i).getRate());
        }
        return newMapRate;
    }

    public static Double findRateByMonth(Long month){
        Double rateValue = getAllRate().get(month);

        return rateValue;
    }
}
