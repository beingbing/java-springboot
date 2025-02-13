package be.springboot.pp.designpattern.behavioral.state.atm.states;

import be.springboot.pp.designpattern.behavioral.state.atm.Atm;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardManagerFactory;
import be.springboot.pp.designpattern.behavioral.state.atm.db.DbAccessor;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.CardType;

public class DispenseCash implements ChangeState {
    private final Atm atm;

    public DispenseCash(Atm atm) {
        this.atm = atm;
    }

    @Override
    public int init() {
        throw new IllegalStateException();
    }

    @Override
    public boolean cancel(int txnId) {
        throw new IllegalStateException();
    }

    @Override
    public boolean readCard(CardDetails cardDetails) {
        throw new IllegalStateException();
    }

    @Override
    public boolean readAmount(CardDetails cardDetails, float amount, int txnId) {
        throw new IllegalStateException();
    }

    @Override
    public float dispenseCash(int txnId) {
        CardType cardType = null;
        // logic to get card-details from DB
        CardManagerFactory.getCardManager(cardType).executeWithdrawal(txnId);
        float amount = DbAccessor.markTxnExecuted(txnId);
        this.atm.changeState(StateFactory.getState(AtmState.EJECT_CARD, this.atm));
        return amount;
    }

    @Override
    public void ejectCard() {
        throw new IllegalStateException();
    }

    @Override
    public AtmState getState() {
        return AtmState.DISPENSE_CASH;
    }
}
