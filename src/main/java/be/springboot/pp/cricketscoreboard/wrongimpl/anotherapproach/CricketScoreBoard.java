package be.springboot.pp.cricketscoreboard.wrongimpl.anotherapproach;

public class CricketScoreBoard {
    private int runs;
    private int wickets;
    private float overs;
    private final ProjectedScoreBoard projectedScoreBoard;

    public CricketScoreBoard(ProjectedScoreBoard projectedScoreBoard) {
        this.projectedScoreBoard = projectedScoreBoard;
    }

    public void updateScore(int runs, int wickets, float overs) {
        this.runs = runs;
        this.wickets = wickets;
        this.overs = overs;
        this.projectedScoreBoard.update(runs, wickets, overs);
    }
}
