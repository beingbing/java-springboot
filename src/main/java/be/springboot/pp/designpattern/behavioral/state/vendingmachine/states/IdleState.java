package be.springboot.pp.designpattern.behavioral.state.vendingmachine.states;

import be.springboot.pp.designpattern.behavioral.state.vendingmachine.VendingMachine;

public class IdleState implements VendingMachineState {
    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("💰 Money inserted. You can now select a product.");
        machine.setState(StateFactory.getState(VendingState.HAS_MONEY, machine));
    }

    @Override
    public void pressButton() {
        System.out.println("❌ Insert money first!");
    }

    @Override
    public void dispense() {
        System.out.println("❌ No product selected.");
    }

    @Override
    public VendingState getState() {
        return VendingState.IDLE;
    }
}
