package be.springboot.pp.boardgames.tictactoe.board;

import be.springboot.pp.boardgames.boardgame.board.Board;
import be.springboot.pp.boardgames.boardgame.move.Move;
import be.springboot.pp.boardgames.boardgame.move.Pair;
import be.springboot.pp.boardgames.boardgame.player.Player;
import be.springboot.pp.boardgames.tictactoe.pieces.Symbol;
import be.springboot.pp.boardgames.tictactoe.player.TicTacToePlayer;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard implements Board {
    private static final int rowCount = 3;
    private static final int columnCount = 3;
    private final List<List<TicTacToeCell>> cells;

    public TicTacToeBoard() {
        this.cells = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            List<TicTacToeCell> ticTacToeRow = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) ticTacToeRow.add(new TicTacToeCell(i, j, Symbol.EMPTY));
            this.cells.add(ticTacToeRow);
        }
    }

    @Override
    public void applyMove(Move move, Player player) {
        TicTacToeCell sourceCell = getCell(move.getSource());
        sourceCell.setSymbol(((TicTacToePlayer)player).getSymbol());
    }

    @Override
    public void display() {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                Pair coordinate = new Pair(row, col);
                Symbol symbol = getCell(coordinate).getSymbol();
                if (symbol == Symbol.EMPTY) System.out.print("  | ");
                else System.out.print(symbol + " | ");
            }
            System.out.println();
        }
    }

    public TicTacToeCell getCell(Pair pair) {
        return this.cells.get(pair.getX()).get(pair.getY());
    }

    public List<List<TicTacToeCell>> getTicTacToeBoardCells() {
        return this.cells;
    }
}
