package be.springboot.pp.concurrency;

import java.util.concurrent.Semaphore;

public class MySemaphore {
    private int permitsLeft;
    private final int maxPermits;

    public MySemaphore(int maxPermits) {
        if (maxPermits < 0) throw new RuntimeException("max-permits < 0");
        this.maxPermits = maxPermits;
        this.permitsLeft = maxPermits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permitsLeft == 0) wait();
        permitsLeft--;
    }

    public synchronized  void release() {
        if (permitsLeft == maxPermits) throw new RuntimeException("Illegal call");
        permitsLeft++;
        if (permitsLeft == 1) notifyAll();
    }
}
/*
* Please note that Semaphore in itself doesn't control the granting of any connection object.
* Or granting of any pool object in general. It simply tracks a count, and some other class
* uses it to grant/release resources from an object. It doesn't hold any list of live connections,
* It simply holds a counter.
*
* Here, you may notice if a permit is acquire by one thread but release by another, it is fine. Because,
* if an acquiring thread dies while holding the permits then it is ok if someone else releases it.
*
* This is because semaphore is not built to take care who is acquiring and who is releasing. It only
* takes care of the fact that not more than a fixed amount of threads should be allowed to process
* a critical section.
*
* Note: If Semaphore count is set to 1, then it acts as a Mutex.
* */

class CharPrinter implements Runnable {
    private final char c;
    private final MySemaphore semaphore;

    CharPrinter(char c, MySemaphore semaphore) {
        this.c = c;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            // critical section: on which only x threads can work simultaneously
            for (int i = 0; i < 10; i++) {
                System.out.println("char: " + c);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
            // release a semaphore whatever the situation be, otherwise it will be a disaster.
        }
    }
}

class MySemaphoreTester {
    public static void main(String[] args) {
        MySemaphore semaphore = new MySemaphore(3);
        Thread t0 = new Thread(new CharPrinter('A', semaphore));
        Thread t1 = new Thread(new CharPrinter('B', semaphore));
        Thread t2 = new Thread(new CharPrinter('C', semaphore));
        Thread t3 = new Thread(new CharPrinter('D', semaphore));
        Thread t4 = new Thread(new CharPrinter('E', semaphore));
        Thread t5 = new Thread(new CharPrinter('F', semaphore));
        Thread t6 = new Thread(new CharPrinter('G', semaphore));
        Thread t7 = new Thread(new CharPrinter('H', semaphore));
        Thread t8 = new Thread(new CharPrinter('I', semaphore));
        Thread t9 = new Thread(new CharPrinter('J', semaphore));
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
    }
}