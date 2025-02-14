package be.springboot.pp.elevator;

import be.springboot.pp.elevator.moves.UnidirectionalMoveStore;

public class ElevatorController {
    private final Elevator elevator = new Elevator(new UnidirectionalMoveStore());

    public void destinationFloor(Floor floor, Direction direction) {
        this.elevator.destine(floor, direction);
    }

    public void openGate() {
        this.elevator.open();
    }

    public void closeGate() {
        this.elevator.close();
    }

    public void stopElevator() {
        this.elevator.stop();
    }
}
