package be.springboot.pp.concurrency;

/*
* Can you make rounds variable static and still get the right answer ?
* */

/*
*
* */

public class Challenge2 {

    public static int cur = 0, rounds = 3;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
        Object lock = new Object();
        Thread t0 = new Thread(new Challenge2Worker(0, lock));
        Thread t1 = new Thread(new Challenge2Worker(1, lock));
        Thread t2 = new Thread(new Challenge2Worker(2, lock));
        Thread t3 = new Thread(new Challenge2Worker(3, lock));
        Thread t4 = new Thread(new Challenge2Worker(4, lock));
        Thread t5 = new Thread(new Challenge2Worker(5, lock));
        Thread t6 = new Thread(new Challenge2Worker(6, lock));
        Thread t7 = new Thread(new Challenge2Worker(7, lock));
        Thread t8 = new Thread(new Challenge2Worker(8, lock));
        Thread t9 = new Thread(new Challenge2Worker(9, lock));
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

    public Challenge2Worker(int val, Object lock) {
        this.val = val;
        this.lock = lock;
    }

//    @Override
//    public void run() {
//        while (Challenge2.rounds > 0) { // this will introduce memory visibility issues
//        while (roundsOver()) { // Thread could have crossed it, and waited in compare() but meanwhile rounds became 0
//            while (compare()) {}
//            System.out.println(Thread.currentThread().getName() + " " + val);
//            synchronized(lock) {
//                Challenge2.cur = (Challenge2.cur + 1) % 10;
//                if (Challenge2.cur == 0)
//                    Challenge2.rounds--;
//            }
//        }
//    }
// This still won't work because roundsOver() is thread safe, decrementing rounds variable is happening
// under thread safe environment. But in between ther are still two lines of command which are happening
// in unsafe environment. Context switch can happen in `while (compare()) {}` and it might happen that
// when thread crossed roundsOver() checked and entered while, the condition was correct, but while waiting
// in while (compare()) {} the condition because incorrect. So, we need to secure that compound action with
// a lock -

    @Override
    public void run() {
        while (true) {
            while (compare()) {}
            synchronized(lock) {
                if (Challenge2.rounds == 0) break;
                System.out.println(Thread.currentThread().getName() + " " + val);
                Challenge2.cur = (Challenge2.cur + 1) % 10;
                if (Challenge2.cur == 0)
                    Challenge2.rounds--;
            }
        }
    }

    private boolean roundsOver() {
        boolean ans = false;
        synchronized(lock) {
            ans = Challenge2.rounds > 0;
        }
        return ans;
    }

    private boolean compare() {
        boolean ans = false;
        synchronized(lock) {
            ans = !(val == Challenge2.cur)
                    && Challenge2.rounds > 0; // need this to inform all busy-waiting threads
        }                                      // that it is time for exiting.
        return ans;
    }
}

// Graceful termination of all the threads is important.