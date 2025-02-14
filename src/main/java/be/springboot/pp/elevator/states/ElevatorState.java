package be.springboot.pp.elevator.states;

import be.springboot.pp.elevator.enums.Direction;
import be.springboot.pp.elevator.dtos.Floor;

public interface ElevatorState {
    void destine(Floor floor, Direction direction);
    void open(Floor floor);
    void close(Floor floor);
    void stop(Floor floor);
    StateType getState();
}
