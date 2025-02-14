package be.springboot.pp.designpattern.behavioral.state.vendingmachine.states;

import be.springboot.pp.designpattern.behavioral.state.vendingmachine.VendingMachine;

public class StateFactory {

    private StateFactory() {}

    public static VendingMachineState getState(VendingState vendingState, VendingMachine vendingMachine) {
        return switch (vendingState) {
            case IDLE -> new IdleState(vendingMachine);
            case HAS_MONEY -> new HasMoneyState(vendingMachine);
            case DISPENSING -> new DispensingState(vendingMachine);
            case OUT_OF_STOCK -> new OutOfStockState(vendingMachine);
        };
    }
}
