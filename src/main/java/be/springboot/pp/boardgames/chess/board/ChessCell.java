package be.springboot.pp.boardgames.chess.board;

import be.springboot.pp.boardgames.chess.pieces.ChessPiece;

import java.util.Optional;

public class ChessCell {
    private final int x;
    private final int y;
    private Optional<ChessPiece> chessPiece;

    public ChessCell(int x, int y) {
        this.x = x;
        this.y = y;
        chessPiece = Optional.empty();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Optional<ChessPiece> getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(Optional<ChessPiece> chessPiece) {
        this.chessPiece = chessPiece;
    }
}
