package be.springboot.pp.designpattern.behavioral.state.vendingmachine;

import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.DispensingState;
import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.HasMoneyState;
import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.IdleState;
import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.OutOfStockState;
import be.springboot.pp.designpattern.behavioral.state.vendingmachine.states.VendingMachineState;

public class VendingMachine {
    private final VendingMachineState idleState;
    private final VendingMachineState hasMoneyState;
    private final VendingMachineState dispensingState;
    private final VendingMachineState outOfStockState;

    private VendingMachineState currentState;
    private int productCount;

    public VendingMachine(int productCount) {
        idleState = new IdleState(this);
        hasMoneyState = new HasMoneyState(this);
        dispensingState = new DispensingState(this);
        outOfStockState = new OutOfStockState(this);

        this.productCount = productCount;
        currentState = productCount > 0 ? idleState : outOfStockState;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void pressButton() {
        currentState.pressButton();
        if (currentState == dispensingState) {
            dispense();
        }
    }

    public void dispense() {
        currentState.dispense();
        if (productCount > 0) {
            productCount--;
        }
    }

    public int getProductCount() {
        return productCount;
    }

    public VendingMachineState getIdleState() { return idleState; }
    public VendingMachineState getHasMoneyState() { return hasMoneyState; }
    public VendingMachineState getDispensingState() { return dispensingState; }
    public VendingMachineState getOutOfStockState() { return outOfStockState; }
}
