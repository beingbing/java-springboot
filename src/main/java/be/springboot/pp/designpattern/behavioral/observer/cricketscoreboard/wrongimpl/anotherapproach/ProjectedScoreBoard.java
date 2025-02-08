package be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.wrongimpl.anotherapproach;

public class ProjectedScoreBoard {
    private int runs;
    private int wickets;
    private float overs;

    public void update(int runs, int wickets, float overs) {
        this.runs = runs;
        this.wickets = wickets;
        this.overs = overs;

        // logic to persist details in DB
        // algo to compute projected score
        // logic to update the board display
    }
}
