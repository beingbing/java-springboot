package be.springboot.pp.concurrency.professorhelper;

public class Professor implements Runnable {
    private final Cabin cabin;
    private int count;

    public Professor(Cabin cabin) {
        this.cabin = cabin;
        count = 0;
    }

    public void reachOut(Student student) {
        cabin.enter(student);
    }

    @Override
    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void work() throws InterruptedException {
        while (true) {
            if (this.count == 10) {
                System.out.println("professor is going out for a walk...");
                Thread.sleep(3000);
                System.out.println("professor is back from the walk");
                this.count = 0;
            }

            Student student = cabin.getStudent();
            Thread.sleep(1000);
            cabin.exit();
            this.count++;
//            System.out.println("Student " + student.getId() + " has finished his/her work");
//            if (cabin.NoStudentWaiting()) {
//                System.out.println("===================== Professor left");
//                break;
//            }
        }
    }
}
