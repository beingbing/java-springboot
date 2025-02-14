package be.springboot.pp.elevator.states;

import be.springboot.pp.elevator.ElevatorManager;
import be.springboot.pp.elevator.dtos.Floor;
import be.springboot.pp.elevator.enums.Direction;
import be.springboot.pp.elevator.moves.Move;

public class MovingUpState implements ElevatorState {
    private final ElevatorManager elevator;

    public MovingUpState(ElevatorManager elevator) {
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
        throw new IllegalStateException("Opening gates not allowed when elevator is moving");
    }

    @Override
    public void close(Floor floor) {
        System.out.println("Elevator gates are already closed");
    }

    @Override
    public void stop(Floor floor) {
        this.elevator.getMoveStore().priorityCleared();
        this.elevator.setState(StateFactory.getState(StateType.IDLE, elevator));
    }

    @Override
    public StateType getState() {
        return StateType.MOVING_UP;
    }
}
