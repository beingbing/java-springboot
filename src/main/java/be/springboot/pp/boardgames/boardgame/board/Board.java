package be.springboot.pp.boardgames.boardgame.board;

import be.springboot.pp.boardgames.boardgame.move.Move;
import be.springboot.pp.boardgames.boardgame.player.Player;

public interface Board {
    void applyMove(Move move, Player player);
    void display();
}
