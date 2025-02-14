package be.springboot.pp.elevator.moves;

import be.springboot.pp.elevator.enums.Direction;
import be.springboot.pp.elevator.dtos.Floor;

public class Move {
    private final Floor destinationFloor;
    private final Direction destinationDirection;

    public Move(Floor destinationFloor, Direction destinationDirection) {
        this.destinationFloor = destinationFloor;
        this.destinationDirection = destinationDirection;
    }

    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDestinationDirection() {
        return destinationDirection;
    }
}
