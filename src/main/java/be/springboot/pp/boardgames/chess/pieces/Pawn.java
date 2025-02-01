package be.springboot.pp.boardgames.chess.pieces;

import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.board.ChessCell;

public class Pawn implements ChessPiece {
    private final Color color;
    private final PieceName name;
    private boolean isDead;

    public Pawn(Color color, PieceName pieceName, boolean isDead) {
        this.color = color;
        this.name = pieceName;
        this.isDead = isDead;
    }

    @Override
    public void move(ChessCell source, ChessCell destination, ChessBoard board) {

    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    @Override
    public PieceName getName() {
        return this.name;
    }

    @Override
    public Color getColor() {
        return null;
    }
}
