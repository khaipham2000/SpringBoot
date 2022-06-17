package vn.techmaster.bank.request;

import vn.techmaster.bank.model.SavingType;

public record SavingAccountRequest (String userId, String accountId, Long months, Long amount, SavingType typeSave) {
    
}
