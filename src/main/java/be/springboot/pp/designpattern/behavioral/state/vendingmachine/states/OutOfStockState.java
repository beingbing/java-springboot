package be.springboot.pp.designpattern.behavioral.state.vendingmachine.states;

import be.springboot.pp.designpattern.behavioral.state.vendingmachine.VendingMachine;

public class OutOfStockState implements VendingMachineState {
    private final VendingMachine machine;

    public OutOfStockState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("❌ Out of stock! Money refunded.");
    }

    @Override
    public void pressButton() {
        System.out.println("❌ Out of stock!");
    }

    @Override
    public void dispense() {
        System.out.println("❌ No product available.");
    }
}
