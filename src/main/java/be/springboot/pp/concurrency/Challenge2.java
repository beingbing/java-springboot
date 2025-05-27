package be.springboot.pp.concurrency;

/*
* Can you make rounds variable static and still get the right answer ?
* */

/*
*
* */

public class Challenge2 {

    public static int cur = 0;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
        Object lock = new Object();
        int rounds = 3;
        Thread t0 = new Thread(new Challenge2Worker(0, lock, rounds));
        Thread t1 = new Thread(new Challenge2Worker(1, lock, rounds));
        Thread t2 = new Thread(new Challenge2Worker(2, lock, rounds));
        Thread t3 = new Thread(new Challenge2Worker(3, lock, rounds));
        Thread t4 = new Thread(new Challenge2Worker(4, lock, rounds));
        Thread t5 = new Thread(new Challenge2Worker(5, lock, rounds));
        Thread t6 = new Thread(new Challenge2Worker(6, lock, rounds));
        Thread t7 = new Thread(new Challenge2Worker(7, lock, rounds));
        Thread t8 = new Thread(new Challenge2Worker(8, lock, rounds));
        Thread t9 = new Thread(new Challenge2Worker(9, lock, rounds));
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

class Challenge2Worker implements Runnable {
    private final int val;
    private final Object lock;
    private int rounds;

    public Challenge2Worker(int val, Object lock, int rounds) {
        this.val = val;
        this.lock = lock;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        while (rounds > 0) {
            while (compare()) {}
            System.out.println(Thread.currentThread().getName() + " " + val);
            synchronized(lock) {
                Challenge2.cur = (Challenge2.cur + 1) % 10;
            }
            rounds--;
        }
    }

    private boolean compare() {
        boolean ans = false;
        synchronized(lock) {
            ans = !(val == Challenge2.cur);
        }
        return ans;
    }
}
