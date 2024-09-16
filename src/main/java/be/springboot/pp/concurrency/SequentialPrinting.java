package be.springboot.pp.concurrency;

public class SequentialPrinting {

    // adding volatile keyword here
    public static volatile int cur = 0;

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
        Thread t0 = new Thread(new Worker(0));
        Thread t1 = new Thread(new Worker(1));
        Thread t2 = new Thread(new Worker(2));
        Thread t3 = new Thread(new Worker(3));
        Thread t4 = new Thread(new Worker(4));
        Thread t5 = new Thread(new Worker(5));
        Thread t6 = new Thread(new Worker(6));
        Thread t7 = new Thread(new Worker(7));
        Thread t8 = new Thread(new Worker(8));
        Thread t9 = new Thread(new Worker(9));
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

class Worker implements Runnable {
    private final int val;

    public Worker(int val) {
        this.val = val;
    }

    @Override
    public void run() {
        while (val > SequentialPrinting.cur) {}
        System.out.println(Thread.currentThread().getName() + " " + val);
        SequentialPrinting.cur++; // it is thread-safe because all threads except one will get stuck in while-loop
    }
}
