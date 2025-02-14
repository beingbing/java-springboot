package be.springboot.pp.designpattern.behavioral.state.atm;

import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.db.DbAccessor;
import be.springboot.pp.designpattern.behavioral.state.atm.states.ChangeState;
import be.springboot.pp.designpattern.behavioral.state.atm.states.StateFactory;

public class Atm {
    private final long atmId;
    private ChangeState atmState;

    public Atm(long atmId) {
        this.atmId = atmId;
        this.atmState = StateFactory.getState(DbAccessor.getAtmState(atmId), this);
    }

    public int init() {
        return this.atmState.init();
    }

    public void changeState(ChangeState newState) {
        this.atmState = newState;
        DbAccessor.updateAtmState(atmId, newState.getState());
    }

    public boolean cancel(int txnId) {
        return this.atmState.cancel(txnId);
    }

    public boolean readCard(CardDetails cardDetails) {
        return this.atmState.readCard(cardDetails);
    }

    public boolean readAmount(CardDetails cardDetails, float amount, int txnId) {
        return this.atmState.readAmount(cardDetails, amount, txnId);
    }

    public float dispenseCash(int txnId) {
        float cash = this.atmState.dispenseCash(txnId);
        this.ejectCard();
        return cash;
    }

    public void ejectCard() {
        this.atmState.ejectCard();
    }

    public long getAtmId() {
        return this.atmId;
    }
}

///*
//* We are still missing handling to ensure that only single
//* txn can happen at a time. For that we can maintain a list
//* of states. Using that, i can stop a new txn if last txn is
//* in any intermediary state.
//* */
//public class Atm {
//    private final long atmId;
//    private AtmState atmState;
//
//    public Atm(long atmId) {
//        this.atmId = atmId;
//        this.atmState = AtmState.READY;
//    }
//
//    public int init() {
//        // this type of if checks will be populated all over the code
//        // also adding a new state require revisiting all existing checks
//        // based on states.
//        // also as each transition depends on what the current state is
//        // according to which transition happens differently.
//        // TODO: need to rethink, this implementation is not good.
//        if (!atmState.equals(AtmState.READY))
//            throw new IllegalCallerException("a txn is already in progress");
//
//        int txnId = 0;
//        // logic to generate a real txnId;
//        this.atmState = AtmState.READ_CARD;
//        return txnId;
//    }
//
//    public boolean cancel(int txnId) {return true;}
//
//    public boolean readCard(String cardType, long cardNum, int pin, String name) {return true;}
//
//    public boolean readWithdrawalDetails(String cardType, long cardNum, int pin, String name, float amount, int txnId) {return true;}
//
//    public boolean dispenseCash(int txnId) {return true;}
//
//    public void ejectCard() {}
//}
