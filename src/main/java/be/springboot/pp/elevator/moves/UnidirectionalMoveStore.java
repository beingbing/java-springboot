package be.springboot.pp.elevator.moves;

import be.springboot.pp.elevator.Direction;

import java.util.Optional;
import java.util.PriorityQueue;

public class UnidirectionalMoveStore implements MoveStore {
    private final PriorityQueue<Move> upMoveMinHeap; // minHeap for moving up
    private final PriorityQueue<Move> downMoveMaxHeap; // maxHeap for moving down
    private Direction currentDirection;

    public UnidirectionalMoveStore() {
        this.upMoveMinHeap = new PriorityQueue<>(
                (f1, f2) -> f1.getDestinationFloor().getNumber() - f2.getDestinationFloor().getNumber());
        this.downMoveMaxHeap = new PriorityQueue<>(
                (f1, f2) -> f2.getDestinationFloor().getNumber() - f1.getDestinationFloor().getNumber());
        this.currentDirection = Direction.HALT;
    }

    @Override
    public void addMove(Move move) {
        if (upMoveMinHeap.isEmpty() && downMoveMaxHeap.isEmpty())
            currentDirection = move.getDestinationDirection();

        if (move.getDestinationDirection().equals(Direction.UP))
            upMoveMinHeap.add(move);
        else downMoveMaxHeap.add(move);
    }

    @Override
    public Optional<Move> getPriorityMove() {
        if (upMoveMinHeap.isEmpty() && downMoveMaxHeap.isEmpty())
            return Optional.empty();

        if (currentDirection.equals(Direction.UP))
            return Optional.of(upMoveMinHeap.peek());

        return Optional.of(downMoveMaxHeap.peek());
    }

    @Override
    public void priorityCleared() {
        if (upMoveMinHeap.isEmpty() && downMoveMaxHeap.isEmpty())
            throw new IllegalStateException("Not a legal move");

        if (currentDirection.equals(Direction.UP)) {
            upMoveMinHeap.poll();
            if (upMoveMinHeap.isEmpty() ) {
                if (!downMoveMaxHeap.isEmpty()) currentDirection = Direction.DOWN;
                else currentDirection = Direction.HALT;
            }
        } else {
            downMoveMaxHeap.poll();
            if (downMoveMaxHeap.isEmpty()) {
                if (!upMoveMinHeap.isEmpty()) currentDirection = Direction.UP;
                else currentDirection = Direction.HALT;
            }
        }
    }

    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }
}
