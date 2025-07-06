package be.springboot.pp.concurrency;

public class SequentialPrinting2 {
    public static int cur = 0;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
        Object lock = new Object();
        int round = 3;
        Thread t0 = new Thread(new SequenceWorker2(0, lock, round));
        Thread t1 = new Thread(new SequenceWorker2(1, lock, round));
        Thread t2 = new Thread(new SequenceWorker2(2, lock, round));
        Thread t3 = new Thread(new SequenceWorker2(3, lock, round));
        Thread t4 = new Thread(new SequenceWorker2(4, lock, round));
        Thread t5 = new Thread(new SequenceWorker2(5, lock, round));
        Thread t6 = new Thread(new SequenceWorker2(6, lock, round));
        Thread t7 = new Thread(new SequenceWorker2(7, lock, round));
        Thread t8 = new Thread(new SequenceWorker2(8, lock, round));
        Thread t9 = new Thread(new SequenceWorker2(9, lock, round));
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

class SequenceWorker2 implements Runnable {
    private final int val;
    private final Object lock;
    private int rounds;

    public SequenceWorker2(int val, Object lock, int rounds) {
        this.val = val;
        this.lock = lock;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        while (rounds > 0) {
            while (compare()) {}
            System.out.println(Thread.currentThread().getName() + " " + val);
            synchronized (lock) {
//                SequentialPrinting.cur++;
                SequentialPrinting2.cur = (SequentialPrinting2.cur + 1) % 10; // 10 is number of threads
            }
            rounds--;
        }
    }

    private boolean compare() {
        boolean ans = false;
        synchronized (lock) {
//            ans = val > SequentialPrinting.cur;
            ans = !(val == SequentialPrinting2.cur); // only thread with val same as cur should be allowed to print
        }
        return ans;
    }
}
