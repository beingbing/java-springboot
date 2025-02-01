package be.springboot.pp.boardgames.chess.pieces;

import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.board.ChessCell;

public class Bishop implements ChessPiece {
    private final Color color;
    private final PieceName name;
    private Boolean isDead;

    public Bishop(Color color, PieceName name, Boolean isDead) {
        this.color = color;
        this.name = name;
        this.isDead = isDead;
    }

    @Override
    public void move(ChessCell source, ChessCell destination, ChessBoard board) {

    }

    @Override
    public boolean isDead() {
        return this.isDead;
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
