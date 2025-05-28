package be.springboot.pp.concurrency;

public class FutureReturnRunnable implements Runnable {
    private String output;
    private boolean isDone;

    public FutureReturnRunnable() {
        this.isDone = false;
    }

    @Override
    public void run() {
        synchronized(this) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            output = "Hello World";
            isDone = true;
        }
    }

    public synchronized String get() throws InterruptedException {
        while (!isDone) wait();
        return output;
    }
}

class FutureTester {
    public static void main(String[] args) throws InterruptedException {
        FutureReturnRunnable runnable = new FutureReturnRunnable();
        new Thread(runnable).start();
        System.out.println("started ...");
        String ans = runnable.get();
        System.out.println("ans: " + ans);
    }
}
