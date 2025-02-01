package be.springboot.pp.boardgames.chess.pieces;

import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.board.ChessCell;

public interface ChessPiece {
    void move(ChessCell source, ChessCell destination, ChessBoard board);
    boolean isDead();
    void setDead(boolean isDead);
    PieceName getName();
    Color getColor();
}
