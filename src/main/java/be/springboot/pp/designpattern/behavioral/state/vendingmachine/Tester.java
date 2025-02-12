package be.springboot.pp.designpattern.behavioral.state.vendingmachine;

public class Tester {

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(2);

        machine.insertMoney();
        machine.pressButton();

        machine.insertMoney();
        machine.pressButton();

        // Now the machine is out of stock
        machine.insertMoney();
    }
}
