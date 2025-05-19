package be.springboot.pp.boardgames.chess.board;

import be.springboot.pp.boardgames.boardgame.move.Pair;
import be.springboot.pp.boardgames.chess.pieces.ChessPiece;

import java.util.Optional;

public class ChessCell extends Pair {
    private Optional<ChessPiece> chessPiece;

    public ChessCell(int x, int y) {
        super(x, y);
        chessPiece = Optional.empty();
    }

    public Optional<ChessPiece> getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(Optional<ChessPiece> chessPiece) {
        this.chessPiece = chessPiece;
    }
}
