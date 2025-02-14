package be.springboot.pp.elevator.states;

import be.springboot.pp.elevator.ElevatorManager;
import be.springboot.pp.elevator.enums.GateStatus;
import be.springboot.pp.elevator.enums.Direction;
import be.springboot.pp.elevator.dtos.Floor;
import be.springboot.pp.elevator.moves.Move;

public class IdleState implements ElevatorState {
    private final ElevatorManager elevator;

    public IdleState(ElevatorManager elevator) {
        this.elevator = elevator;
    }

    @Override
    public void destine(Floor floor, Direction direction) {
        this.elevator.getMoveStore().addMove(new Move(floor, direction));
        Direction currentDirection = this.elevator.getMoveStore().getPriorityMove().get().getDestinationDirection();
        switch (currentDirection) {
            case UP -> this.elevator.setState(StateFactory.getState(StateType.MOVING_UP, elevator));
            case DOWN -> this.elevator.setState(StateFactory.getState(StateType.MOVING_DOWN, elevator));
            case HALT -> this.elevator.setState(StateFactory.getState(StateType.IDLE, elevator));
        }
    }

    @Override
    public void open(Floor floor) {
        this.elevator.setGateStatus(GateStatus.OPEN);
        this.elevator.setState(StateFactory.getState(StateType.GATE_OPEN, elevator));
    }

    @Override
    public void close(Floor floor) {
        System.out.println("Elevator gates are already closed");
    }

    @Override
    public void stop(Floor floor) {
        System.out.println("Elevator is already stationary");
    }

    @Override
    public StateType getState() {
        return StateType.IDLE;
    }
}
