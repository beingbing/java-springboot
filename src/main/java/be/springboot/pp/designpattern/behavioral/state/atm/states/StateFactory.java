package be.springboot.pp.designpattern.behavioral.state.atm.states;

import be.springboot.pp.designpattern.behavioral.state.atm.Atm;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;

public class StateFactory {

    private StateFactory() {}

    public static ChangeState getState(AtmState state, Atm atm) {
        return switch (state) {
            case READY -> new ReadyState(atm);
            case READ_CARD -> new ReadCardState(atm);
            case READ_AMOUNT -> new ReadAmountState(atm);
            case DISPENSE_CASH -> new DispenseCash(atm);
            case EJECT_CARD -> new EjectCard(atm);
        };
    }
}
