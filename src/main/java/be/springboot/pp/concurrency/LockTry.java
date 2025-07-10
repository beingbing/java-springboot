package be.springboot.pp.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTry {
    public static void main(String[] args) {
        ReentrantLock l1 = new ReentrantLock();
        ReentrantLock l2 = new ReentrantLock();
        Thread t1 = new Thread(new TryWorker("Thread-1", l1, l2, 1000));
        Thread t2 = new Thread(new TryWorker("Thread-2", l2, l1, 2000));
        t1.start();
        t2.start();
    }
}

class TryWorker implements Runnable {
    private final Lock firstLock;
    private final Lock secondLock;
    private final int waitTime;
    private final String name;

    public TryWorker(String name, Lock firstLock, Lock secondLock, int waitTime) {
        this.name = name;
        this.firstLock = firstLock;
        this.secondLock = secondLock;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        int attempts = 0;
        while (attempts < 20) {
            if (firstLock.tryLock()) {
                try {
                    System.out.println(name + " acquired first lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}

                    if (secondLock.tryLock()) {
                        try {
                            System.out.println(name + " acquired both locks! Done.");
                            return;
                        } finally {
                            secondLock.unlock();
                        }
                    } else {
                        System.out.println(name + " could not acquire second lock, releasing first...");
                    }
                } finally {
                    firstLock.unlock();
                }
            } else {
                System.out.println(name + " could not acquire first lock");
            }

            attempts++;
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ignored) {}
        }

        System.out.println(name + " gave up after too many attempts.");
    }
}
