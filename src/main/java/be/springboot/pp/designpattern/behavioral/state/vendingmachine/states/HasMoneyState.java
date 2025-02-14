package be.springboot.pp.designpattern.behavioral.state.vendingmachine.states;

import be.springboot.pp.designpattern.behavioral.state.vendingmachine.VendingMachine;

public class HasMoneyState implements VendingMachineState {
    private final VendingMachine machine;

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("❌ Money already inserted.");
    }

    @Override
    public void pressButton() {
        System.out.println("✔ Product selected. Dispensing...");
        machine.setState(StateFactory.getState(VendingState.DISPENSING, machine));
        machine.getState().dispense();
    }

    @Override
    public void dispense() {
        System.out.println("❌ Press the button to get your product.");
    }

    @Override
    public VendingState getState() {
        return VendingState.HAS_MONEY;
    }
}
