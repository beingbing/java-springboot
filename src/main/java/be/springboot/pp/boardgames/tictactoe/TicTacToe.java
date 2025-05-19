package be.springboot.pp.boardgames.tictactoe;

import be.springboot.pp.boardgames.boardgame.BoardGame;
import be.springboot.pp.boardgames.tictactoe.board.TicTacToeBoard;
import be.springboot.pp.boardgames.tictactoe.board.TicTacToeCell;
import be.springboot.pp.boardgames.tictactoe.pieces.Symbol;
import be.springboot.pp.boardgames.tictactoe.player.TicTacToePlayer;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class TicTacToe extends BoardGame {
    private final List<List<TicTacToeCell>> boardCells;

    public TicTacToe(TicTacToeBoard board, TicTacToePlayer firstPlayer, TicTacToePlayer secondPlayer) {
        super(board, new ArrayDeque<>(Arrays.asList(firstPlayer, secondPlayer)));
        this.boardCells = board.getTicTacToeBoardCells();
    }

    @Override
    public boolean isOver() {
        return hasWinningLine(boardCells); // || isBoardFull();
    }

    private boolean hasWinningLine(List<List<TicTacToeCell>> board) {
        for (int i = 0; i < 3; i++) {
            // Check row
            if (board.get(i).get(0).getSymbol() != Symbol.EMPTY
                    && board.get(i).get(0).getSymbol().equals(board.get(i).get(1).getSymbol())
                    && board.get(i).get(1).getSymbol().equals(board.get(i).get(2).getSymbol())) {
                return true;
            }

            // Check column
            if (board.get(0).get(i).getSymbol() != Symbol.EMPTY
                    && board.get(0).get(i).getSymbol().equals(board.get(1).get(i).getSymbol())
                    && board.get(1).get(i).getSymbol().equals(board.get(2).get(i).getSymbol())) {
                return true;
            }
        }

        // Check main diagonal
        if (board.get(0).get(0).getSymbol() != Symbol.EMPTY
                && board.get(0).get(0).getSymbol().equals(board.get(1).get(1).getSymbol())
                && board.get(1).get(1).getSymbol().equals(board.get(2).get(2).getSymbol())) {
            return true;
        }

        // Check aux diagonal
        if (board.get(0).get(2).getSymbol() != Symbol.EMPTY
                && board.get(0).get(2).getSymbol().equals(board.get(1).get(1).getSymbol())
                && board.get(1).get(1).getSymbol().equals(board.get(2).get(0).getSymbol())) {
            return true;
        }

        return false;
    }
}
