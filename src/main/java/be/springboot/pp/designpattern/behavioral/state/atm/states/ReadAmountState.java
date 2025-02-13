package be.springboot.pp.designpattern.behavioral.state.atm.states;

import be.springboot.pp.designpattern.behavioral.state.atm.Atm;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardManagerFactory;
import be.springboot.pp.designpattern.behavioral.state.atm.db.DbAccessor;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.TxnStatus;

public class ReadAmountState implements ChangeState {
    private final Atm atm;

    public ReadAmountState(Atm atm) {
        this.atm = atm;
    }

    @Override
    public int init() {
        throw new IllegalStateException();
    }

    @Override
    public boolean cancel(int txnId) {
        DbAccessor.saveTxnCancellation(txnId);
        this.atm.changeState(StateFactory.getState(AtmState.EJECT_CARD, this.atm));
        return true;
    }

    @Override
    public boolean readCard(CardDetails cardDetails) {
        throw new IllegalStateException();
    }

    @Override
    public boolean readAmount(CardDetails cardDetails, float amount, int txnId) {
        boolean isAmountValid = CardManagerFactory
                .getCardManager(cardDetails.getCardType())
                .validateWithdrawalAmount(cardDetails, amount, txnId);
        if (!isAmountValid) {
            DbAccessor.saveWithdrawal(txnId, amount, TxnStatus.INVALID);
            this.atm.changeState(StateFactory.getState(AtmState.EJECT_CARD, this.atm));
            return false;
        }
        DbAccessor.saveWithdrawal(txnId, amount, TxnStatus.APPROVED);
        this.atm.changeState(StateFactory.getState(AtmState.DISPENSE_CASH, this.atm));
        return true;
    }

    @Override
    public float dispenseCash(int txnId) {
        throw new IllegalStateException();
    }

    @Override
    public void ejectCard() {
        throw new IllegalStateException();
    }

    @Override
    public AtmState getState() {
        return AtmState.READ_AMOUNT;
    }
}
