package be.springboot.pp.concurrency;

public class EvenOddPrinter {
    public static int current = 1;     // Starting number (can be any value)
    public static int limit = 2_00_000;      // Ending number (inclusive)
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread even1Thread = new Thread(new NewEvenWorker(lock), "1-Even-Thread");
        Thread even2Thread = new Thread(new NewEvenWorker(lock), "2-Even-Thread");
        Thread odd1Thread = new Thread(new NewOddWorker(lock), "1-Odd--Thread");
        Thread odd2Thread = new Thread(new NewOddWorker(lock), "2-Odd--Thread");

        even1Thread.start();
        even2Thread.start();
        odd1Thread.start();
        odd2Thread.start();
    }
}

class NewEvenWorker implements Runnable {
    private final Object lock;

    NewEvenWorker(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (EvenOddPrinter.current > EvenOddPrinter.limit) break;

                if (EvenOddPrinter.current % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + EvenOddPrinter.current);
                    EvenOddPrinter.current++;
                    lock.notifyAll(); // Wake up odd thread
                } else {
                    try {
                        lock.wait(); // Wait if it's not even's turn
                    } catch (InterruptedException e) {
                        System.out.println("EvenWorker interrupted");
                    }
                }
            }
        }
    }
}

class NewOddWorker implements Runnable {
    private final Object lock;

    NewOddWorker(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (EvenOddPrinter.current > EvenOddPrinter.limit) break;

                if (EvenOddPrinter.current % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + " " + EvenOddPrinter.current);
                    EvenOddPrinter.current++;
                    lock.notifyAll(); // Wake up even thread
                } else {
                    try {
                        lock.wait(); // Wait if it's not odd's turn
                    } catch (InterruptedException e) {
                        System.out.println("EvenWorker interrupted");
                    }
                }
            }
        }
    }
}
