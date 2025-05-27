package be.springboot.pp.concurrency;

/*
* Problem Statement: A Modification request came in, the sequential printing needs
* to be done for certain number of rounds. E.g., if round is 3, then the sequential
* number from 1-n should be printed 3 times. Each round should be individually sorted in itself.
* */

/*
* For this every thread should know for how
* */

public class Challenge1 {

    public static int cur = 0;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
        Object lock = new Object();
        int round = 3;
        Thread t0 = new Thread(new Challenge1Worker(0, lock));
        Thread t1 = new Thread(new Challenge1Worker(1, lock));
        Thread t2 = new Thread(new Challenge1Worker(2, lock));
        Thread t3 = new Thread(new Challenge1Worker(3, lock));
        Thread t4 = new Thread(new Challenge1Worker(4, lock));
        Thread t5 = new Thread(new Challenge1Worker(5, lock));
        Thread t6 = new Thread(new Challenge1Worker(6, lock));
        Thread t7 = new Thread(new Challenge1Worker(7, lock));
        Thread t8 = new Thread(new Challenge1Worker(8, lock));
        Thread t9 = new Thread(new Challenge1Worker(9, lock));
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
        System.out.println(Thread.currentThread().getName() + " ended");
    }
}

class Challenge1Worker implements Runnable {
    private final int val;
    private final Object lock;

    public Challenge1Worker(int val, Object lock) {
        this.val = val;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (compare()) {}
         System.out.println(Thread.currentThread().getName() + " " + val);
         synchronized(lock) {
             Challenge1.cur++;
         }
    }

     private boolean compare() {
         boolean ans = false;
         synchronized(lock) {
             ans = val > Challenge1.cur;
         }
         return ans;
     }
}
