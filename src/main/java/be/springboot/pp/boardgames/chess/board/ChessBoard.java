package be.springboot.pp.boardgames.chess.board;

import be.springboot.pp.boardgames.boardgame.board.Board;
import be.springboot.pp.boardgames.boardgame.move.Move;
import be.springboot.pp.boardgames.boardgame.move.Pair;
import be.springboot.pp.boardgames.boardgame.player.Player;
import be.springboot.pp.boardgames.chess.pieces.Bishop;
import be.springboot.pp.boardgames.chess.pieces.ChessPiece;
import be.springboot.pp.boardgames.chess.pieces.Color;
import be.springboot.pp.boardgames.chess.pieces.King;
import be.springboot.pp.boardgames.chess.pieces.Knight;
import be.springboot.pp.boardgames.chess.pieces.Pawn;
import be.springboot.pp.boardgames.chess.pieces.PieceName;
import be.springboot.pp.boardgames.chess.pieces.Queen;
import be.springboot.pp.boardgames.chess.pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoard implements Board {
    private final List<List<ChessCell>> cells;
    private static final int rowCount = 8; // static because all instances of chessBoard will have 8 rows
    private static final int columnCount = 8;

    public ChessBoard() {
        this.cells = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            List<ChessCell> chessCells = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) chessCells.add(new ChessCell(i, j));
            this.cells.add(chessCells);
        }
    }

    @Override
    public void applyMove(Move move, Player player) {
        ChessCell sourceCell = getCell(move.getSource());
        ChessPiece chessPiece = sourceCell.getChessPiece().get();
        chessPiece.move(sourceCell, getCell(move.getDestination()), this);
    }

    @Override
    public void display() {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                Pair p = new Pair(row, col);
                Optional<ChessPiece> chessPieceOptional = getCell(p).getChessPiece();
                if (chessPieceOptional.isEmpty()) {
                    System.out.print("0 | ");
                    continue;
                }
                ChessPiece chessPiece = chessPieceOptional.get();
                System.out.print(chessPiece.getName() + " | ");
            }
            System.out.println();
        }
    }

    public ChessCell getCell(Pair pair) {
        return this.cells.get(pair.getX()).get(pair.getY());
    }

    public ChessCell getCell(ChessCell cell) {
        return this.cells.get(cell.getX()).get(cell.getY());
    }

    public void putPiece(ChessPiece chessPiece, int row, int col) {
        ChessCell initialCell = getCell(new Pair(row, col));
        initialCell.setChessPiece(Optional.of(chessPiece));
    }

    public void putPiece(ChessPiece chessPiece, ChessCell cell) {
        ChessCell initialCell = getCell(cell);
        initialCell.setChessPiece(Optional.of(chessPiece));
    }

    public void removePiece(ChessCell cell) {
        ChessCell holdingCell = getCell(cell);
        holdingCell.setChessPiece(Optional.empty());
    }

    public Map<PieceName, ChessPiece> generatePieces(Color color) {
        Map<PieceName, ChessPiece> pieces = new HashMap<>();
        pieces.put(PieceName.BISHOP1, new Bishop(color, PieceName.BISHOP1, false));
        pieces.put(PieceName.BISHOP2, new Bishop(color, PieceName.BISHOP2, false));
        pieces.put(PieceName.KNIGHT1, new Knight(color, PieceName.KNIGHT1, false));
        pieces.put(PieceName.KNIGHT2, new Knight(color, PieceName.KNIGHT2, false));
        pieces.put(PieceName.ROOK1, new Rook(color, PieceName.ROOK1, false));
        pieces.put(PieceName.ROOK2, new Rook(color, PieceName.ROOK2, false));
        pieces.put(PieceName.KING, new King(color, PieceName.KING, false));
        pieces.put(PieceName.QUEEN, new Queen(color, PieceName.QUEEN, false));
        pieces.put(PieceName.PAWN1, new Pawn(color, PieceName.PAWN1, false));
        pieces.put(PieceName.PAWN2, new Pawn(color, PieceName.PAWN2, false));
        pieces.put(PieceName.PAWN3, new Pawn(color, PieceName.PAWN3, false));
        pieces.put(PieceName.PAWN4, new Pawn(color, PieceName.PAWN4, false));
        pieces.put(PieceName.PAWN5, new Pawn(color, PieceName.PAWN5, false));
        pieces.put(PieceName.PAWN6, new Pawn(color, PieceName.PAWN6, false));
        pieces.put(PieceName.PAWN7, new Pawn(color, PieceName.PAWN7, false));
        pieces.put(PieceName.PAWN8, new Pawn(color, PieceName.PAWN8, false));

        return pieces;
    }
}
