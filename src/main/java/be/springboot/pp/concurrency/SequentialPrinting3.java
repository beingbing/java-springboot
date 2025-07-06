package be.springboot.pp.concurrency;

public class SequentialPrinting3 {
    public static int cur = 0; // Shared counter to keep track of which thread's turn it is (0 to 9)

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");

        Object lock = new Object(); // Shared lock for synchronization
        int rounds = 3;             // Total number of times to print 0 to 9

        for (int i = 0; i < 10; i++) { // Create and start 10 threads
            Thread t = new Thread(new SequenceWorker3(i, lock, rounds));
            t.start();
        }

        System.out.println(Thread.currentThread().getName() + " ended");
    }
}

class SequenceWorker3 implements Runnable {
    private final int val;      // The value (0 to 9) that this thread prints
    private final Object lock;  // Shared lock object
    private int rounds;         // Remaining rounds for this thread

    public SequenceWorker3(int val, Object lock, int rounds) {
        this.val = val;
        this.lock = lock;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        while (rounds > 0) {
            synchronized (lock) {
                while (val != SequentialPrinting3.cur) { // Wait until it's this thread's turn to print
                    try {
                        lock.wait(); // Release lock and wait for notification
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                    }
                }

                System.out.println(Thread.currentThread().getName() + " " + val); // It's this thread's turn to print
                SequentialPrinting3.cur = (SequentialPrinting3.cur + 1) % 10; // Move to the next thread in sequence
                lock.notifyAll(); // Notify all waiting threads to check if it's their turn
                rounds--; // One round of this number is done
            }
        }
    }
}
