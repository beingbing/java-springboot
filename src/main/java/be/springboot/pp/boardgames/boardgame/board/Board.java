package be.springboot.pp.boardgames.boardgame.board;

import be.springboot.pp.boardgames.boardgame.move.Move;

public interface Board {
    void applyMove(Move move);
    void display();
}
