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
        System.out.println("DispenseCash: init");
        throw new IllegalStateException();
    }

    @Override
    public boolean cancel(int txnId) {
        System.out.println("DispenseCash: cancel");
        throw new IllegalStateException();
    }

    @Override
    public boolean readCard(CardDetails cardDetails) {
        System.out.println("DispenseCash: readCard");
        throw new IllegalStateException();
    }

    @Override
    public boolean readAmount(CardDetails cardDetails, float amount, int txnId) {
        System.out.println("DispenseCash: readAmount");
        throw new IllegalStateException();
    }

    @Override
    public float dispenseCash(int txnId) {
        System.out.println("DispenseCash: dispenseCash");
        CardType cardType = null;
        // logic to get card-details from DB
        cardType = CardType.DEBIT;
        CardManagerFactory.getCardManager(cardType).executeWithdrawal(txnId);
        float amount = DbAccessor.markTxnExecuted(txnId);
        this.atm.changeState(StateFactory.getState(AtmState.EJECT_CARD, this.atm));
        return amount;
    }

    @Override
    public void ejectCard() {
        System.out.println("DispenseCash: ejectCard");
        throw new IllegalStateException();
    }

    @Override
    public AtmState getState() {
        return AtmState.DISPENSE_CASH;
    }
}
