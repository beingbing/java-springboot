package be.springboot.pp.designpattern.behavioral.state.vendingmachine.states;

public interface VendingMachineState {
    void insertMoney();
    void pressButton();
    void dispense();
}
