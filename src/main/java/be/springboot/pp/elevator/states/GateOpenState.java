package be.springboot.pp.elevator.states;

import be.springboot.pp.elevator.Elevator;
import be.springboot.pp.elevator.Floor;
import be.springboot.pp.elevator.Direction;

public class GateOpenState implements ElevatorState {
    private final Elevator elevator;

    public GateOpenState(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void destine(Floor floor, Direction direction) {
        throw new IllegalStateException("can not request for movement to destination in open gate state");
    }

    @Override
    public void open() {
        System.out.println("Elevator gate is already open");
    }

    @Override
    public void close() {
        Direction direction = this.elevator.getMoveStore().getCurrentDirection();
        switch (direction) {
            case UP -> this.elevator.setState(StateFactory.getState(StateType.MOVING_UP, elevator));
            case DOWN -> this.elevator.setState(StateFactory.getState(StateType.MOVING_DOWN, elevator));
            case HALT -> this.elevator.setState(StateFactory.getState(StateType.IDLE, elevator));
        }
    }

    @Override
    public void stop() {
        System.out.println("Elevator is already stationary and gates are open");
    }

    @Override
    public StateType getState() {
        return StateType.GATE_OPEN;
    }
}
