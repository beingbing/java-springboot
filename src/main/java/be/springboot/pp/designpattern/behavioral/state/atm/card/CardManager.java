package be.springboot.pp.designpattern.behavioral.state.atm.card;

public interface CardManager {
    boolean validateCard(CardDetails cardDetails);
    boolean validateWithdrawalAmount(CardDetails cardDetails, float amount, int txnId);
    void executeWithdrawal(int txnId);
}
