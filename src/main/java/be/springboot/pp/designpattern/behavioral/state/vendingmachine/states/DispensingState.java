package be.springboot.pp.designpattern.behavioral.state.vendingmachine.states;

import be.springboot.pp.designpattern.behavioral.state.vendingmachine.VendingMachine;

public class DispensingState implements VendingMachineState {
    private final VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("❌ Please wait, dispensing in progress.");
    }

    @Override
    public void pressButton() {
        System.out.println("❌ Already dispensing.");
    }

    @Override
    public void dispense() {
        System.out.println("🎁 Product dispensed.");
        if (machine.getProductCount() > 0) {
            machine.setState(machine.getIdleState());
        } else {
            machine.setState(machine.getOutOfStockState());
        }
    }
}
