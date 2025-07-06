package be.springboot.pp.concurrency;

public class SequentialPrinting4 {
    public static int cur = 0;           // Whose turn to print (0–9)
    public static int rounds = 3;        // Shared round count across threads

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");

        Object lock = new Object(); // Shared lock for synchronization

        for (int i = 0; i < 10; i++) { // Create and start 10 threads
            Thread t = new Thread(new SequenceWorker4(i, lock));
            t.start();
        }

        System.out.println(Thread.currentThread().getName() + " ended");
    }
}

class SequenceWorker4 implements Runnable {
    private final int val;      // This thread's number to print
    private final Object lock;  // Shared lock

    public SequenceWorker4(int val, Object lock) {
        this.val = val;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (SequentialPrinting4.rounds == 0) break; // If no more rounds left, exit
                while (val != SequentialPrinting4.cur) { // Wait for this thread's turn
                    try {
                        if (SequentialPrinting4.rounds == 0) return; // Before waiting, re-check if rounds ended
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (SequentialPrinting4.rounds == 0) break; // check for Spurious Wakeups and Missed Wakeups
                System.out.println(Thread.currentThread().getName() + " " + val); // Print current value
                SequentialPrinting4.cur = (SequentialPrinting4.cur + 1) % 10; // Move to next in sequence
                if (val == 9) SequentialPrinting4.rounds--; // If we completed a full round (i.e., last number), decrement round
                lock.notifyAll(); // Notify all other threads
            }
        }
    }
}
