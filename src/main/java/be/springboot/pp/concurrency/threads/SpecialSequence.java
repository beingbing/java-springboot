package be.springboot.pp.concurrency.threads;

public class SpecialSequence {
    public static final int n = 255;
    public static int count = 1;
    public static boolean isZeroTurn = true;

    public static void main(String[] args) {
        Object lock = new Object();

        Thread zero = new Thread(new Zero(lock), "Zero");
        Thread odd = new Thread(new Odd(lock), "Odd");
        Thread even = new Thread(new Even(lock), "Even");

        zero.start();
        odd.start();
        even.start();
    }
}

class Zero implements Runnable {
    private final Object lock;

    public Zero(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while (!SpecialSequence.isZeroTurn && SpecialSequence.count <= SpecialSequence.n) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignored) {}
                }

                if (SpecialSequence.count > SpecialSequence.n) {
                    lock.notifyAll();
                    break;
                }

                System.out.print(0 + " ");
                SpecialSequence.isZeroTurn = false;
                lock.notifyAll();
            }
        }
    }
}

class Odd implements Runnable {
    private final Object lock;

    public Odd(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while ((SpecialSequence.isZeroTurn || SpecialSequence.count % 2 == 0)
                        && SpecialSequence.count <= SpecialSequence.n) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignored) {}
                }

                if (SpecialSequence.count > SpecialSequence.n) {
                    lock.notifyAll();
                    break;
                }

                System.out.print(SpecialSequence.count + " ");
                SpecialSequence.count++;
                SpecialSequence.isZeroTurn = true;
                lock.notifyAll();
            }
        }
    }
}

class Even implements Runnable {
    private final Object lock;

    public Even(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while ((SpecialSequence.isZeroTurn || SpecialSequence.count % 2 != 0)
                        && SpecialSequence.count <= SpecialSequence.n) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignored) {}
                }

                if (SpecialSequence.count > SpecialSequence.n) {
                    lock.notifyAll();
                    break;
                }

                System.out.print(SpecialSequence.count + " ");
                SpecialSequence.count++;
                SpecialSequence.isZeroTurn = true;
                lock.notifyAll();
            }
        }
    }
}
