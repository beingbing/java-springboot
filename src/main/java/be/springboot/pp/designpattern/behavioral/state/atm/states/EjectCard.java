package be.springboot.pp.designpattern.behavioral.state.atm.states;

import be.springboot.pp.designpattern.behavioral.state.atm.Atm;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;

public class EjectCard implements ChangeState {
    private final Atm atm;

    public EjectCard(Atm atm) {
        this.atm = atm;
    }

    @Override
    public int init() {
        System.out.println("EjectCard: init");
        throw new IllegalStateException();
    }

    @Override
    public boolean cancel(int txnId) {
        System.out.println("EjectCard: cancel");
        throw new IllegalStateException();
    }

    @Override
    public boolean readCard(CardDetails cardDetails) {
        System.out.println("EjectCard: readCard");
        throw new IllegalStateException();
    }

    @Override
    public boolean readAmount(CardDetails cardDetails, float amount, int txnId) {
        System.out.println("EjectCard: readAmount");
        throw new IllegalStateException();
    }

    @Override
    public float dispenseCash(int txnId) {
        System.out.println("EjectCard: dispenseCash");
        throw new IllegalStateException();
    }

    @Override
    public void ejectCard() {
        System.out.println("EjectCard: ejectCard");
        this.atm.changeState(StateFactory.getState(AtmState.READY, this.atm));
    }

    @Override
    public AtmState getState() {
        return AtmState.EJECT_CARD;
    }
}
