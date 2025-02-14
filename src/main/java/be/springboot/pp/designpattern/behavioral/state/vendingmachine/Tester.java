package be.springboot.pp.designpattern.behavioral.state.vendingmachine;

public class Tester {

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(2);
        System.out.println(machine.getState().getState());

        machine.insertMoney();
        System.out.println(machine.getState().getState());
        machine.pressButton();
        System.out.println(machine.getState().getState());

        machine.insertMoney();
        System.out.println(machine.getState().getState());
        machine.pressButton();
        System.out.println(machine.getState().getState());

        // Now the machine is out of stock
        machine.insertMoney();
        System.out.println(machine.getState().getState());
    }
}
