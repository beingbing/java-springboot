package be.springboot.pp.boardgames.chess.player;

import be.springboot.pp.boardgames.boardgame.move.Move;
import be.springboot.pp.boardgames.boardgame.move.Pair;
import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.pieces.ChessPiece;
import be.springboot.pp.boardgames.chess.pieces.PieceName;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class HumanChessPlayer extends ChessPlayer {
    public HumanChessPlayer(String name, ChessBoard board, Map<PieceName, ChessPiece> pieces) {
        super(name, board, pieces);
    }

    @Override
    public Move makeMove() {
        int x, y;
        Scanner scanner = new Scanner(System.in);
        x = scanner.nextInt();
        y = scanner.nextInt();
        Pair source = new Pair(x, y);
        Optional<ChessPiece> chessPieceOptional =  getChessBoard().getCell(source).getChessPiece();
        if (chessPieceOptional.isEmpty()) throw new IllegalArgumentException("Unknown chessman");
        ChessPiece chessPiece = chessPieceOptional.get();
        if (!chessPiece.equals(getPiece(chessPiece.getName()))) throw new IllegalArgumentException("Illegal piece");
        x = scanner.nextInt();
        y = scanner.nextInt();
        Pair destination = new Pair(x, y);
        // validation on destination
        return new Move(source, destination);
    }
}
