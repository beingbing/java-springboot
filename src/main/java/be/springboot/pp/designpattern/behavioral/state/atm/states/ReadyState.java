package be.springboot.pp.designpattern.behavioral.state.atm.states;

import be.springboot.pp.designpattern.behavioral.state.atm.Atm;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.db.DbAccessor;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;

public class ReadyState implements ChangeState {
    private final Atm atm;

    public ReadyState(Atm atm) {
        this.atm = atm;
    }

    @Override
    public int init() {
        System.out.println("ReadyState: init");
        int txnId = DbAccessor.createNewTxnId(this.atm.getAtmId());
        if (txnId == 0) throw new RuntimeException("Failed to initiate the txn");
        this.atm.changeState(new ReadCardState(this.atm));
        return txnId;
    }

    @Override
    public boolean cancel(int txnId) {
        System.out.println("ReadyState: cancel");
        throw new IllegalStateException("Currently in Ready state, can not cancel a txn until a new txn starts");
    }

    @Override
    public boolean readCard(CardDetails cardDetails) {
        System.out.println("ReadyState: read-card");
        throw new IllegalStateException("Currently in Ready state, can not read card");
    }

    @Override
    public boolean readAmount(CardDetails cardDetails, float amount, int txnId) {
        System.out.println("ReadyState: read-amount");
        throw new IllegalStateException("Currently in Ready state, can not read amount");
    }

    @Override
    public float dispenseCash(int txnId) {
        System.out.println("ReadyState: dispense-cash");
        throw new IllegalStateException("Currently in Ready state, can not dispense cash");
    }

    @Override
    public void ejectCard() {
        System.out.println("ReadyState: eject-card");
        throw new IllegalStateException("Currently in Ready state, can not eject card");
    }

    @Override
    public AtmState getState() {
        return AtmState.READY;
    }
}
