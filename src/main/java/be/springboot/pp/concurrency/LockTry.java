package be.springboot.pp.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTry {
    public static void main(String[] args) {
        ReentrantLock l1 = new ReentrantLock();
        ReentrantLock l2 = new ReentrantLock();
        Thread t1 = new Thread(new TryWorker(l1, l2, 1000));
        Thread t2 = new Thread(new TryWorker(l2, l1, 2000));
        t1.start();
        t2.start();
    }
}

class TryWorker implements Runnable {
    private final Lock l1, l2;
    private final int waitTime;

    TryWorker(Lock l1, Lock l2, int waitTime) {
        this.l1 = l1;
        this.l2 = l2;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        while (true) {
            if (l1.tryLock()) {
                try {
                    Thread.sleep(1000);
                    if (l2.tryLock()) {
                        try {
                            System.out.println("Acquired both ...");
                            return;
                        } finally {
                            l2.unlock();
                        }
                    } else {
                        System.out.println("Encountered a deadlock ...");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    l1.unlock();
                }
            }
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}