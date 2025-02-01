package be.springboot.pp.boardgames.boardgame.player;

import be.springboot.pp.boardgames.boardgame.move.Move;

public abstract class Player {
    private final String name;

    protected Player(String name) {
        this.name = name;
    }

    public abstract Move makeMove();

    public String getName() {
        return this.name;
    }
}
