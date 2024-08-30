package be.springboot.pp.springmvc.dto;

public class ExamResult {
    private final int total;
    private final int physics;
    private final int chemistry;
    private final int maths;
    private final int maxScore = 300;

    public ExamResult(int physics, int chemistry, int maths) {
        this.total = physics + chemistry + maths;
        this.physics = physics;
        this.chemistry = chemistry;
        this.maths = maths;
    }

    public int getTotal() {
        return total;
    }

    public int getPhysics() {
        return physics;
    }

    public int getChemistry() {
        return chemistry;
    }

    public int getMaths() {
        return maths;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
