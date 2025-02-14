package be.springboot.pp.elevator;

import be.springboot.pp.elevator.moves.MoveStore;
import be.springboot.pp.elevator.states.ElevatorState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Elevator {
    public Floor currentFloor;
    private ElevatorState state;
    private final MoveStore moveStore;

    public Elevator(MoveStore moveStore) {
        this.moveStore = moveStore;
    }

    public void destine(Floor floor, Direction direction) {
        this.state.destine(floor, direction);
    }

    public void open() {
        this.state.open();
    }

    public void close() {
        this.state.close();
    }

    public void stop() {
        this.state.stop();
    }
}
