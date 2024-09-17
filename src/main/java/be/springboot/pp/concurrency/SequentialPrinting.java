package be.springboot.pp.concurrency;

public class SequentialPrinting {

    // adding volatile keyword here
    public static int cur = 0;

    /*
    * The main program never completes, it keeps on running and do not
    * print anything as well. Something is wrong.
    *
    * Let's first go through a concept of how CPU(h/w) executes threads -
    * A machine has multiple CPUs, assume Thread t1 got scheduled on core c1, then
    * t1 will make copy of complete context in c1 memory (because of performance benefits)
    * on which it needs to work on, once that's done, final result is written back in RAM
    * to the actual variable. This writing back of updated value in RAM in called flushing.
    * But this flushing also happen from time to time as well. It is where problem originates,
    * what if when a certain thread has updated local variable in c1 but fails to flush it
    * to RAM. Then other threads which are reading it from RAM won't be getting updated value.
    * This phenomenon is known as 'Memory Visibility Problem'.
    *
    * There is no guarantee of if a thread makes changes to a certain variable then it will
    * be timely reflected to other threads. This is what's happening with out program as well.
    *
    * While other threads were waiting for value of 'cur' to be updated, the thread t0 updated
    * it to 1 but wasn't able to flush it for other threads to become visible.
    *
    * Java offers a keyword for such a shared variable which needs to be updated inherently,
    * without making its local copy in CPU core. This keyword is `volatile`.
    *
    * 'volatile' ensures that any changes made by a thread gets immediately flushed to main
    * memory, ensuring high visibility.
    *
    * 'volatile' is rarely used in today's time, as there are alternate ways of maintaining
    * memory visibility.
    * */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
        Object lock = new Object();
        Thread t0 = new Thread(new SequenceWorker(0, lock));
        Thread t1 = new Thread(new SequenceWorker(1, lock));
        Thread t2 = new Thread(new SequenceWorker(2, lock));
        Thread t3 = new Thread(new SequenceWorker(3, lock));
        Thread t4 = new Thread(new SequenceWorker(4, lock));
        Thread t5 = new Thread(new SequenceWorker(5, lock));
        Thread t6 = new Thread(new SequenceWorker(6, lock));
        Thread t7 = new Thread(new SequenceWorker(7, lock));
        Thread t8 = new Thread(new SequenceWorker(8, lock));
        Thread t9 = new Thread(new SequenceWorker(9, lock));
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

class SequenceWorker implements Runnable {
    private final int val;
    private final Object lock;

    public SequenceWorker(int val, Object lock) {
        this.val = val;
        this.lock = lock;
    }

    /*
    * skipping using volatile keyword and instead using 'synchronized' keyword
    * */
    @Override
    public void run() {
        while (compare()) {}
        System.out.println(Thread.currentThread().getName() + " " + val);
        synchronized(lock) {
            SequentialPrinting.cur++; // it is thread-safe because all threads except one will get stuck in while-loop
        }
    }

    private boolean compare() {
        boolean ans = false;
        synchronized(lock) {
            ans = val > SequentialPrinting.cur;
        }
        return ans;
    }
}
