package vn.techmaster.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.bank.model.Rate;

public interface RateRepo extends JpaRepository<Rate,String> {
    
}
