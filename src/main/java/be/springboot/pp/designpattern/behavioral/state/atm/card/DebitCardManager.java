package be.springboot.pp.designpattern.behavioral.state.atm.card;

public class DebitCardManager implements CardManager {
    @Override
    public boolean validateCard(CardDetails cardDetails) {
        return false;
    }

    @Override
    public boolean validateWithdrawalAmount(CardDetails cardDetails, float amount, int txnId) {
        return false;
    }

    @Override
    public void executeWithdrawal(int txnId) {

    }
}
