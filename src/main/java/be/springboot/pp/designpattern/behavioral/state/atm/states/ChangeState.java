package be.springboot.pp.designpattern.behavioral.state.atm.states;

import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;

public interface ChangeState {
    int init();

    boolean cancel(int txnId);

    boolean readCard(CardDetails cardDetails);

    boolean readAmount(CardDetails cardDetails, float amount, int txnId);

    float dispenseCash(int txnId);

    void ejectCard();

    AtmState getState();
}
