package be.springboot.pp.elevator.states;

import be.springboot.pp.elevator.Direction;
import be.springboot.pp.elevator.Floor;

public interface ElevatorState {
    void destine(Floor floor, Direction direction);
    void open();
    void close();
    void stop();
    StateType getState();
}
