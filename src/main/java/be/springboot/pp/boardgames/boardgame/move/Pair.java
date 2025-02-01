package be.springboot.pp.boardgames.boardgame.move;

public class Pair {
    private final int x;
    private final int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
