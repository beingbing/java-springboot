package be.springboot.pp.elevator;

import be.springboot.pp.elevator.dtos.Floor;
import be.springboot.pp.elevator.enums.Direction;
import be.springboot.pp.elevator.moves.UnidirectionalMoveStore;
import be.springboot.pp.elevator.states.StateFactory;
import be.springboot.pp.elevator.states.StateType;

public class Tester {

    public static void main(String[] args) {
        ElevatorManager elevator = new ElevatorManager(new UnidirectionalMoveStore());
        elevator.setState(StateFactory.getState(StateType.IDLE, elevator));
        elevator.setCurrentFloor(new Floor(0, "Ground"));
        elevator.destine(new Floor(4, "Fourth"), Direction.UP);
        System.out.println(elevator.getState().getState());

        elevator.destine(new Floor(7, "Seventh"), Direction.UP);

        elevator.setCurrentFloor(new Floor(1, "First"));
        elevator.setCurrentFloor(new Floor(2, "Second"));
        elevator.setCurrentFloor(new Floor(3, "Third"));

        elevator.destine(new Floor(1, "First"), Direction.DOWN);

        elevator.setCurrentFloor(new Floor(4, "Fourth"));

        elevator.stop(new Floor(4, "Fourth"));
        System.out.println(elevator.getState().getState());

        elevator.open(new Floor(4, "Fourth"));
        System.out.println(elevator.getState().getState());

        elevator.close(new Floor(4, "Fourth"));
        System.out.println(elevator.getState().getState());

        System.out.println(elevator.getMoveStore().getPriorityMove().get().getDestinationFloor()
                + " " + elevator.getMoveStore().getPriorityMove().get().getDestinationDirection());

        elevator.destine(new Floor(3, "Third"), Direction.DOWN);

        elevator.setCurrentFloor(new Floor(7, "Seventh"));
        elevator.stop(new Floor(7, "Seventh"));
        System.out.println(elevator.getState().getState());

        elevator.open(new Floor(7, "Seventh"));
        System.out.println(elevator.getState().getState());

        elevator.close(new Floor(7, "Seventh"));
        System.out.println(elevator.getState().getState());

        System.out.println(elevator.getMoveStore().getPriorityMove().get().getDestinationFloor()
                + " " + elevator.getMoveStore().getPriorityMove().get().getDestinationDirection());
    }
}
