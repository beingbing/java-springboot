package be.springboot.pp.designpattern.behavioral.state.vendingmachine;

import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.StateFactory;
import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.VendingMachineState;
import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.VendingState;

public class VendingMachine {
    private VendingMachineState state;
    private int productCount;

    public VendingMachine(int productCount) {
        this.productCount = productCount;
        state = productCount > 0
                ? StateFactory.getState(VendingState.IDLE, this)
                    : StateFactory.getState(VendingState.OUT_OF_STOCK, this);
    }

    public void insertMoney() {
        state.insertMoney();
    }

    public void pressButton() {
        state.pressButton();
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public VendingMachineState getState() {
        return state;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }
}
