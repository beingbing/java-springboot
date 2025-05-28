package be.springboot.pp.concurrency;

// skipping barrier completely

public class MyBarrier {
    private final int numThreads;
    private int count;
    private final Runnable barrierTask;

    public MyBarrier(int numThreads, Runnable barrierTask) {
        this.numThreads = numThreads;
        this.count = numThreads;
        this.barrierTask = barrierTask;
    }

    public synchronized void await() throws InterruptedException {
        count--;
        if (count > 0) while(count > 0) wait();
        else {
            barrierTask.run();
            count = numThreads;
            notifyAll();
        }
    }
}
