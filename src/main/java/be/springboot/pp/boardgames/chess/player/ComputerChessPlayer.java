package be.springboot.pp.boardgames.chess.player;

import be.springboot.pp.boardgames.boardgame.move.Move;
import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.pieces.ChessPiece;
import be.springboot.pp.boardgames.chess.pieces.PieceName;

import java.util.Map;

public class ComputerChessPlayer extends ChessPlayer {
    public ComputerChessPlayer(String name, ChessBoard board, Map<PieceName, ChessPiece> pieces) {
        super(name, board, pieces);
    }

    @Override
    public Move makeMove() {
        return null;
    }
}
