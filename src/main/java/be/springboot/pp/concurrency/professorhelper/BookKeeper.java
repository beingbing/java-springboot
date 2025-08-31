package be.springboot.pp.concurrency.professorhelper;

public class BookKeeper implements Runnable{
    private final Cabin cabin;

    public BookKeeper(Cabin cabin) {
        this.cabin = cabin;
    }

    @Override
    public void run() {
        while (true) {
            cabin.log();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (!cabin.NoStudentWaiting()) break;
        }
    }
}
