package be.springboot.pp.boardgames;

import be.springboot.pp.boardgames.boardgame.BoardGame;
import be.springboot.pp.boardgames.tictactoe.TicTacToe;
import be.springboot.pp.boardgames.tictactoe.board.TicTacToeBoard;
import be.springboot.pp.boardgames.tictactoe.pieces.Symbol;
import be.springboot.pp.boardgames.tictactoe.player.TicTacToePlayer;

public class TicTacToeTester {

    public static void main(String[] args) {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        TicTacToePlayer playerX = new TicTacToePlayer("Samar", ticTacToeBoard, Symbol.X);
        TicTacToePlayer playerO = new TicTacToePlayer("Maheen", ticTacToeBoard, Symbol.O);
        BoardGame ticTacToe = new TicTacToe(ticTacToeBoard, playerX, playerO);
        ticTacToe.startGame();
    }
}
