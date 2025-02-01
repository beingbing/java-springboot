package be.springboot.pp.boardgames.chess.player;

import be.springboot.pp.boardgames.boardgame.player.Player;
import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.pieces.PieceName;
import be.springboot.pp.boardgames.chess.pieces.ChessPiece;

import java.util.Map;

public abstract class ChessPlayer extends Player {
    private final ChessBoard chessBoard;
    private final Map<PieceName, ChessPiece> pieces;

    protected ChessPlayer(String name, ChessBoard initialSetup, Map<PieceName, ChessPiece> pieces) {
        super(name);
        this.chessBoard = initialSetup;
        this.pieces = pieces;
    }

    public ChessPiece getPiece(PieceName name) {
        if (!pieces.containsKey(name)) throw new IllegalArgumentException("Unknown chessman");
        return this.pieces.get(name);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
