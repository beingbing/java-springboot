package be.springboot.pp.concurrency;
// 16
public class SpecialSequence {

    /* Problem statement:
    * given n, print n natural number such that before every number a zero must be present.
    * */
    public static final int n = 11;

    public static int i = 1;

    public static void main(String[] args) {
//        System.out.println("SpecialSequence: main: args: " + args + " " + Thread.currentThread().getName());
        Object lock = new Object();
        Thread even = new Thread(new Even(lock));
        Thread odd = new Thread(new Odd(lock));
        Thread zero = new Thread(new Zero(lock));
        even.start();
        odd.start();
        zero.start();
//        System.out.println("SpecialSequence: main: ends " + Thread.currentThread().getName());
    }
}

class Even implements Runnable {

    private final Object lock;

    public Even(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
//        System.out.println("Even: run: " + Thread.currentThread().getName());
        synchronized(lock) {
            while (SpecialSequence.i <= 2 * SpecialSequence.n) {
                if (SpecialSequence.i % 2 != 0) {
//                    System.out.println("busy waiting inside even: " + Thread.currentThread().getName());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println("even ex: " + e.getMessage());
                    }
                } else {
                    System.out.print(SpecialSequence.i / 2);
                    System.out.print(" ");
                    SpecialSequence.i++;
                    lock.notifyAll();
                }
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
//        System.out.println("Odd: run: " + Thread.currentThread().getName());
        synchronized(lock) {
            while (SpecialSequence.i <= 2 * SpecialSequence.n) {
                if (SpecialSequence.i % 2 != 0) {
//                    System.out.println("busy waiting inside odd: " + Thread.currentThread().getName());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println("odd ex: " + e.getMessage());
                    }
                } else {
                    System.out.print(SpecialSequence.i / 2);
                    System.out.print(" ");
                    SpecialSequence.i++;
                    lock.notifyAll();
                }
            }
        }
    }
}

class Zero implements Runnable {

    private final Object lock;

    public Zero(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
//        System.out.println("Zero: run: " + Thread.currentThread().getName());
        synchronized(lock) {
            while (SpecialSequence.i <= 2 * SpecialSequence.n) {
                if (SpecialSequence.i % 2 != 0) {
                    System.out.print(0);
                    System.out.print(" ");
                    SpecialSequence.i++;
                } else {
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println("zero ex: " + e.getMessage());
                    }
                }
            }
        }
    }
}