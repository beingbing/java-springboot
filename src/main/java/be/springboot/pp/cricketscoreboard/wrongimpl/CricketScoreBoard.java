package be.springboot.pp.cricketscoreboard.wrongimpl;

/*
* There is a status api in which we receive latest values for each respective field
* and the process handling that api call inject the update data into an object of
* below class using updateScore().
* */
public class CricketScoreBoard {
    private int runs;
    private int wickets;
    private float overs;

    public void updateScore(int runs, int wickets, float overs) {
        this.runs = runs;
        this.wickets = wickets;
        this.overs = overs;
    }

    public int getRuns() {
        return this.runs;
    }

    public int getWickets() {
        return this.wickets;
    }

    public float getOvers() {
        return this.overs;
    }
}
