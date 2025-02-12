package be.springboot.pp.designpattern.behavioral.state.atm.wrongimplementation;

public enum AtmState {
    READY,
    READ_CARD,
    READ_AMOUNT,
    DISPENSE_CASH,
    EJECT_CARD
}
