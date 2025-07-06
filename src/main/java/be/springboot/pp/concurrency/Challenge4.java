package be.springboot.pp.concurrency;

/*
* You have 2 shared variables, cur (starting from 1) and limit, some threads should print all even
* numbers and other remaining threads all odd number in given range.
* Each number should be printed only once, but we have a choice for who wants to print it.
* It should not happen that a thread is printing both even and odd numbers.
* */

public class Challenge4 {
    public static final int limit = 20;
    public static int cur = 1;

    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(new EvenWorker("first even", lock));
        Thread t3 = new Thread(new EvenWorker("second even", lock));
        Thread t2 = new Thread(new OddWorker("first Odd", lock));
        Thread t4 = new Thread(new OddWorker("second Odd", lock));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
// nothing need to change, it will work correctly