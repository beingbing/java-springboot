package be.springboot.pp.elevator.states;

import be.springboot.pp.elevator.Elevator;

public class StateFactory {

    private StateFactory() {}

    public static ElevatorState getState(StateType stateType, Elevator elevator) {
        return switch (stateType) {
            case IDLE -> new IdleState(elevator);
            case GATE_OPEN -> new GateOpenState(elevator);
            case MOVING_DOWN -> new MovingDownState(elevator);
            case MOVING_UP -> new MovingUpState(elevator);
        };
    }
}
