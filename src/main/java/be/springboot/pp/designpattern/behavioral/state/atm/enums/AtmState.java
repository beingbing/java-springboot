package be.springboot.pp.designpattern.behavioral.state.atm.enums;

public enum AtmState {
    READY,
    READ_CARD,
    READ_AMOUNT,
    DISPENSE_CASH,
    EJECT_CARD
}
