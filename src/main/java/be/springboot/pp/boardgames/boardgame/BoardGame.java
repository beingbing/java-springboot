package be.springboot.pp.boardgames.boardgame;

import be.springboot.pp.boardgames.boardgame.board.Board;
import be.springboot.pp.boardgames.boardgame.move.Move;
import be.springboot.pp.boardgames.boardgame.player.Player;

import java.util.Queue;

public abstract class BoardGame {

    private final Board board;
    private final Queue<Player> players;// for multiple players to play turn by turn, queue is standard way to do it

    public BoardGame(Board board, Queue<Player> players) {
        this.board = board;
        this.players = players;
    }

    public void startGame() {
        while (true) {
            board.display();
            Player currentPlayer = players.poll();
            Move move = currentPlayer.makeMove();
            board.applyMove(move);

            if (isOver()) {
                System.out.println("Game Over... " + currentPlayer.getName() + " won!!");
                break;
            }
            players.add(currentPlayer);
        }
    }

    public abstract boolean isOver();
}
