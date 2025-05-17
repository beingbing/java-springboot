package be.springboot.pp.boardgames.tictactoe.player;

import be.springboot.pp.boardgames.boardgame.move.Move;
import be.springboot.pp.boardgames.boardgame.move.Pair;
import be.springboot.pp.boardgames.boardgame.player.Player;
import be.springboot.pp.boardgames.tictactoe.board.TicTacToeBoard;
import be.springboot.pp.boardgames.tictactoe.pieces.Symbol;

import java.util.Scanner;

public class TicTacToePlayer extends Player {
    private final TicTacToeBoard board;
    private final Symbol symbol;

    public TicTacToePlayer(String name, TicTacToeBoard initialSetup, Symbol symbol) {
        super(name);
        this.board = initialSetup;
        this.symbol = symbol;
    }

    @Override
    public Move makeMove() {
        int x, y;
        while (true) {
            System.out.print("Player " + getName() + " turn: ");
            Scanner scanner = new Scanner(System.in);
            x = scanner.nextInt();
            y = scanner.nextInt();
            Symbol symbol = board.getCell(new Pair(x, y)).getSymbol();
            if (symbol.equals(Symbol.EMPTY)) break;
            else System.out.println("Cell is not empty");
        }
        Pair source = new Pair(x, y);
        return new Move(source, null);
    }

    public Symbol getSymbol() {
        return this.symbol;
    }
}
