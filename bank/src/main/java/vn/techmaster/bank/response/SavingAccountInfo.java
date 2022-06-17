package vn.techmaster.bank.response;

import java.time.LocalDateTime;

public record SavingAccountInfo(String savingAccId, Long startBalance, Long month, Double rate, LocalDateTime openAt, LocalDateTime closeAt) {
    
    
}
