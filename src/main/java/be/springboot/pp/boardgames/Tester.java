package be.springboot.pp.boardgames;

import be.springboot.pp.boardgames.boardgame.BoardGame;
import be.springboot.pp.boardgames.chess.Chess;
import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.pieces.Color;
import be.springboot.pp.boardgames.chess.player.ChessPlayer;
import be.springboot.pp.boardgames.chess.player.HumanChessPlayer;

public class Tester {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        ChessPlayer whitePlayer = new HumanChessPlayer("Samar", chessBoard, chessBoard.generatePieces(Color.WHITE));
        ChessPlayer blackPlayer = new HumanChessPlayer("Maheen", chessBoard, chessBoard.generatePieces(Color.BLACK));
        BoardGame chess = new Chess(chessBoard, whitePlayer, blackPlayer);
        chess.startGame();
    }
}
