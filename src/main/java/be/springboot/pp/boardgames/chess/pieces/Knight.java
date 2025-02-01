package be.springboot.pp.boardgames.chess.pieces;

import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.board.ChessCell;

public class Knight implements ChessPiece {
    private final Color color;
    private final PieceName name;
    private Boolean isDead;

    public Knight(Color color, PieceName name, boolean isDead) {
        this.color = color;
        this.name = name;
        this.isDead = isDead;
    }

    @Override
    public void move(ChessCell source, ChessCell destination, ChessBoard board) {
        int xDiff = Math.abs(source.getX() - destination.getX());
        int yDiff = Math.abs(source.getY() - destination.getY());

        // Illegal move
        if (!(Math.max(xDiff, yDiff) == 2 && Math.min(xDiff, yDiff) == 1)) throw new RuntimeException("Invalid Move");

        if (destination.getChessPiece().isPresent()) {
            ChessPiece destinationChessPiece = destination.getChessPiece().get();
            if (destinationChessPiece.getColor().equals(this.color)) {
                throw new RuntimeException("Destination Invalid. Contains own piece");
            } else {
                destinationChessPiece.setDead(true);
            }
        }

        board.removePiece(source);
        board.putPiece(this, destination);
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
        return this.color;
    }
}
