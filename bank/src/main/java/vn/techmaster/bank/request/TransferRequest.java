package vn.techmaster.bank.request;

public record TransferRequest(String userId, String sendAccId, String receiveAccId, Long amount ) {
    
}
