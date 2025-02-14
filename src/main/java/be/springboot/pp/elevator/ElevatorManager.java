package be.springboot.pp.elevator;

import be.springboot.pp.elevator.dtos.Floor;
import be.springboot.pp.elevator.enums.Direction;
import be.springboot.pp.elevator.enums.GateStatus;
import be.springboot.pp.elevator.moves.MoveStore;
import be.springboot.pp.elevator.states.ElevatorState;

public class ElevatorManager {
    public Floor currentFloor;
    private ElevatorState state;
    private final MoveStore moveStore;
    private GateStatus gateStatus;

    public ElevatorManager(MoveStore moveStore) {
        this.moveStore = moveStore;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public MoveStore getMoveStore() {
        return moveStore;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }

    public void destine(Floor floor, Direction direction) {
        this.state.destine(floor, direction);
    }

    public void open(Floor floor) {
        this.state.open(floor);
    }

    public void close(Floor floor) {
        this.state.close(floor);
    }

    public void stop(Floor floor) {
        this.state.stop(floor);
    }
}
