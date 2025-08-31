package be.springboot.pp.concurrency.professorhelper;

public class Tester {
    public static void main(String[] args) throws InterruptedException {
        Cabin cabin = new Cabin(3);
        Professor professor = new Professor(cabin);
        Thread professorThread = new Thread(professor);
        professorThread.start();
        for (int i = 0; i < 3; i++) {
            Student student = new Student(i, Batch.A, professor);
            new Thread(student).start();
        }
        Thread.sleep(2000);
        for (int i = 3; i < 10; i++) {
            Student student = new Student(i, Batch.B, professor);
            new Thread(student).start();
        }
        for (int i = 10; i < 16; i++) {
            Student student = new Student(i, Batch.A, professor);
            new Thread(student).start();
        }
        for (int i = 16; i < 20; i++) {
            Student student = new Student(i, Batch.B, professor);
            new Thread(student).start();
        }
        BookKeeper bk = new BookKeeper(cabin);
        new Thread(new BookKeeper(cabin)).start();
    }
}
