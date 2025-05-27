package be.springboot.pp.concurrency;

 /* Like nested loops we can have nested synchronized blocks as well. But
 Same lock when acquired in reverse by any other thread leads to deadlock.
 As now both threads are waiting for other to leave the remaining lock.

This is our third and final evil -
- Race condition
- memory visibility issues
- deadlock
 */

public class Deadlock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread t1 = new Thread(new Run1(lock1, lock2));
        Thread t2 = new Thread(new Run2(lock1, lock2));
        t1.start();
        t2.start();
    }
}

class Run1 implements Runnable {
    private final Object lock1, lock2;

    Run1(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized(lock1) {
            for (int x = 0; x < 10000000; x++) {}
            synchronized(lock2) {
                System.out.println("Run1 printed");
            }
        }
    }
}

class Run2 implements Runnable {
    private final Object lock1, lock2;

    Run2(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized(lock2) {
            for (int x = 0; x < 10000000; x++) {}
            synchronized(lock1) {
                System.out.println("Why Run2 Why");
            }
        }
    }
}
