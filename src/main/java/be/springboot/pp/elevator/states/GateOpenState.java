package be.springboot.pp.elevator.states;

import be.springboot.pp.elevator.enums.GateStatus;
import be.springboot.pp.elevator.enums.Direction;
import be.springboot.pp.elevator.ElevatorManager;
import be.springboot.pp.elevator.dtos.Floor;

public class GateOpenState implements ElevatorState {
    private final ElevatorManager elevator;

    public GateOpenState(ElevatorManager elevator) {
        this.elevator = elevator;
    }

    @Override
    public void destine(Floor floor, Direction direction) {
        throw new IllegalStateException("can not request for movement to destination in open gate state");
    }

    @Override
    public void open(Floor floor) {
        System.out.println("Elevator gate is already open");
    }

    @Override
    public void close(Floor floor) {
        this.elevator.setGateStatus(GateStatus.CLOSE);
        Direction direction = this.elevator.getMoveStore().getCurrentDirection();
        switch (direction) {
            case UP -> this.elevator.setState(StateFactory.getState(StateType.MOVING_UP, elevator));
            case DOWN -> this.elevator.setState(StateFactory.getState(StateType.MOVING_DOWN, elevator));
            case HALT -> this.elevator.setState(StateFactory.getState(StateType.IDLE, elevator));
        }
    }

    @Override
    public void stop(Floor floor) {
        System.out.println("Elevator is already stationary and gates are open");
    }

    @Override
    public StateType getState() {
        return StateType.GATE_OPEN;
    }
}
