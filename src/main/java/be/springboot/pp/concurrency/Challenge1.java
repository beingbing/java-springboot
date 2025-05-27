package be.springboot.pp.concurrency;

/*
 * Problem Statement: A Modification request came in, the sequential printing needs
 * to be done for certain number of rounds. E.g., if round is 3, then the sequential
 * number from 1-n should be printed 3 times. Each round should be individually sorted in itself.
 * */

/*
 * For this every thread should know for how many rounds it will be running, thus we need to
 * provide round info to each thread.
 *
 * */

public class Challenge1 {

    public static int cur = 0;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
        Object lock = new Object();
        int rounds = 3;
        Thread t0 = new Thread(new Challenge1Worker(0, lock, rounds));
        Thread t1 = new Thread(new Challenge1Worker(1, lock, rounds));
        Thread t2 = new Thread(new Challenge1Worker(2, lock, rounds));
        Thread t3 = new Thread(new Challenge1Worker(3, lock, rounds));
        Thread t4 = new Thread(new Challenge1Worker(4, lock, rounds));
        Thread t5 = new Thread(new Challenge1Worker(5, lock, rounds));
        Thread t6 = new Thread(new Challenge1Worker(6, lock, rounds));
        Thread t7 = new Thread(new Challenge1Worker(7, lock, rounds));
        Thread t8 = new Thread(new Challenge1Worker(8, lock, rounds));
        Thread t9 = new Thread(new Challenge1Worker(9, lock, rounds));
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
    private int rounds;

    public Challenge1Worker(int val, Object lock, int rounds) {
        this.val = val;
        this.lock = lock;
        this.rounds = rounds;
    }

//    @Override
//    public void run() {
//        while (rounds > 0) {
//            while (compare()) {}
//            System.out.println(Thread.currentThread().getName() + " " + val);
//            synchronized(lock) {
//                Challenge1.cur++;
//            }
//            rounds--;
//        }
//    }
    // This won't work because, although java is pass-by-value so each thread will get
    // its own copy of rounds variable. But compare function ensures that the thread whose
    // val > cur will stay trapped. Hence, it might happen that each thread which didn't
    // get stuck runs multiple times. So, output might look like -
    // 0, 0, 1, 0, 2, 1, 2, 3, ...
    // Thus val > cur was working well in single printing because once thread prints, it
    // exits, but here, every thread is trying to print again. So, if not prevented, each
    // thread once escaping from compare() will keep on printing if context-switch didn't
    // happen. So we need to change our compare condition to -
    // ans = !(val == Challenge1.cur)
    // Indicating only thread with value same as cur should be allowed to print.
    // Also, to keep loop going, keep cur increment wrapping thus -
    // Challenge1.cur = (Challenge1.cur + 1) % n;
    // here n is 10.

    @Override
    public void run() {
        while (rounds > 0) {
            while (compare()) {}
            System.out.println(Thread.currentThread().getName() + " " + val);
            synchronized(lock) {
//                Challenge1.cur++;
                Challenge1.cur = (Challenge1.cur + 1) % 10;
            }
            rounds--;
        }
    }

    private boolean compare() {
        boolean ans = false;
        synchronized(lock) {
//            ans = val > Challenge1.cur;
            ans = !(val == Challenge1.cur);
        }
        return ans;
    }
}
