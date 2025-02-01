package be.springboot.pp.boardgames.chess;

import be.springboot.pp.boardgames.boardgame.BoardGame;
import be.springboot.pp.boardgames.boardgame.player.Player;
import be.springboot.pp.boardgames.chess.board.ChessBoard;
import be.springboot.pp.boardgames.chess.pieces.PieceName;
import be.springboot.pp.boardgames.chess.player.ChessPlayer;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class Chess extends BoardGame {
    private final ChessPlayer whitePlayer, blackPlayer;

    public Chess(ChessBoard board, ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        super(board, new ArrayDeque<Player>(Arrays.asList(whitePlayer, blackPlayer)));
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        prepareBoard(board);
    }

    @Override
    public boolean isOver() {
        return whitePlayer.getPiece(PieceName.KING).isDead()
                || blackPlayer.getPiece(PieceName.KING).isDead();
    }

    private void prepareBoard(ChessBoard board) {
        placeRooks(0, board, whitePlayer);
        placeKnights(0, board, whitePlayer);
        placeBishops(0, board, whitePlayer);
        placeKing(0, board, whitePlayer);
        placeQueen(0, board, whitePlayer);
        placePawns(1, board, whitePlayer);

        placePawns(6, board, blackPlayer);
        placeRooks(7, board, blackPlayer);
        placeKnights(7, board, blackPlayer);
        placeBishops(7, board, blackPlayer);
        placeKing(7, board, blackPlayer);
        placeQueen(7, board, blackPlayer);
    }

    private void placePawns(int row, ChessBoard board, ChessPlayer player) {
        List<PieceName> pieceNameList = Arrays.asList(
                PieceName.PAWN1,
                PieceName.PAWN2,
                PieceName.PAWN3,
                PieceName.PAWN4,
                PieceName.PAWN5,
                PieceName.PAWN6,
                PieceName.PAWN7,
                PieceName.PAWN8
        );

        int col = 0;
        for (PieceName pieceName : pieceNameList) board.putPiece(player.getPiece(pieceName), row, col++);
    }

    private void placeRooks(int row, ChessBoard board, ChessPlayer player) {
        board.putPiece(player.getPiece(PieceName.ROOK1), row, 0);
        board.putPiece(player.getPiece(PieceName.ROOK2), row, 7);
    }

    private void placeKnights(int row, ChessBoard board, ChessPlayer player) {
        board.putPiece(player.getPiece(PieceName.KNIGHT1), row, 1);
        board.putPiece(player.getPiece(PieceName.KNIGHT2), row, 6);
    }

    private void placeBishops(int row, ChessBoard board, ChessPlayer player) {
        board.putPiece(player.getPiece(PieceName.BISHOP1), row, 2);
        board.putPiece(player.getPiece(PieceName.BISHOP2), row, 5);
    }

    private void placeKing(int row, ChessBoard board, ChessPlayer player) {
        board.putPiece(player.getPiece(PieceName.KING), row, 3);
    }

    private void placeQueen(int row, ChessBoard board, ChessPlayer player) {
        board.putPiece(player.getPiece(PieceName.QUEEN), row, 4);
    }
}
