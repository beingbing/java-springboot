package be.springboot.pp.concurrency.professorhelper;

public class Student implements Runnable {
    private final int id;
    private final Batch batch;
    private final Professor professor;

    public Student(int id, Batch batch, Professor professor) {
        this.id = id;
        this.batch = batch;
        this.professor = professor;
    }

    @Override
    public void run() {
        seekHelp();
    }

    private void seekHelp() {
        professor.reachOut(this);
    }

    public int getId() {
        return this.id;
    }

    public Batch getBatch() {
        return this.batch;
    }
}
