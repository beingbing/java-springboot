package be.springboot.pp.boardgames.tictactoe.board;

import be.springboot.pp.boardgames.boardgame.move.Pair;
import be.springboot.pp.boardgames.tictactoe.pieces.Symbol;
import lombok.ToString;

@ToString
public class TicTacToeCell extends Pair {
    private Symbol symbol;

    public TicTacToeCell(int x, int y, Symbol symbol) {
        super(x, y);
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
