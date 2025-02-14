package be.springboot.pp.elevator.moves;

import be.springboot.pp.elevator.Direction;

import java.util.Optional;

public interface MoveStore {
    void addMove(Move move);
    Optional<Move> getPriorityMove();
    void priorityCleared();
    Direction getCurrentDirection();
}
