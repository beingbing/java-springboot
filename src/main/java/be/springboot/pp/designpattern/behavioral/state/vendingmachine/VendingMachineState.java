package be.springboot.pp.designpattern.behavioral.state.vendingmachine;

public interface VendingMachineState {
    void insertMoney();
    void pressButton();
    void dispense();
}
