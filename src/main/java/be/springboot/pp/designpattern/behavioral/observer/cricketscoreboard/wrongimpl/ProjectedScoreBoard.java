package be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.wrongimpl;

public class ProjectedScoreBoard {
    private int runs;
    private int wickets;
    private float overs;
    private final CricketScoreBoard cricketScoreBoard;

    public ProjectedScoreBoard(CricketScoreBoard cricketScoreBoard) {
        this.cricketScoreBoard = cricketScoreBoard;
    }

    public void update(int runs, int wickets, float overs) {
        while (true) {
            boolean isUpdated = false;

            int updatedRuns = this.cricketScoreBoard.getRuns();
            if (updatedRuns != this.runs) {
                this.runs = updatedRuns;
                isUpdated = true;
            }

            int updatedWickets = this.cricketScoreBoard.getWickets();
            if (updatedWickets != this.wickets) {
                this.wickets = updatedWickets;
                isUpdated = true;
            }

            float updatedOvers = this.cricketScoreBoard.getOvers();
            if (updatedOvers != this.overs) {
                this.overs = updatedOvers;
                isUpdated = true;
            }

            if (isUpdated) {
                // logic to persist details in DB
                // algo to compute projected score
                // logic to update the board display
            }
        }
    }
}
