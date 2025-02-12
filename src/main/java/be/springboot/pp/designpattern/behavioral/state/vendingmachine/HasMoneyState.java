package be.springboot.pp.designpattern.behavioral.state.vendingmachine;

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
        machine.setState(machine.getDispensingState());
    }

    @Override
    public void dispense() {
        System.out.println("❌ Press the button to get your product.");
    }
}
