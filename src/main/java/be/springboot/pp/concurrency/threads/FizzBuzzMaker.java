package be.springboot.pp.concurrency.threads;

public class FizzBuzzMaker {
    public static final int n = 25;
    public static int i = 1;

    public static void main(String[] args) {
        Object lock = new Object();

        Thread fizz = new Thread(new Fizz(lock), "Fizz");
        Thread buzz = new Thread(new Buzz(lock), "Buzz");
        Thread fizzbuzz = new Thread(new FizzBuzz(lock), "FizzBuzz");
        Thread plain = new Thread(new Plain(lock), "Plain");

        fizz.start();
        buzz.start();
        fizzbuzz.start();
        plain.start();
    }
}

class Fizz implements Runnable { // 3, !5
    private final Object lock;
    public Fizz(Object lock) { this.lock = lock; }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (FizzBuzzMaker.i > FizzBuzzMaker.n) break;
                if (FizzBuzzMaker.i % 3 == 0 && FizzBuzzMaker.i % 5 != 0) {
                    System.out.println(FizzBuzzMaker.i + " Fizz");
                    FizzBuzzMaker.i++;
                    lock.notifyAll();
                } else {
                    try { lock.wait(); } catch (InterruptedException _) {}
                }
            }
        }
    }
}

class Buzz implements Runnable { // !3, 5
    private final Object lock;
    public Buzz(Object lock) { this.lock = lock; }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (FizzBuzzMaker.i > FizzBuzzMaker.n) break;
                if (FizzBuzzMaker.i % 3 != 0 && FizzBuzzMaker.i % 5 == 0) {
                    System.out.println(FizzBuzzMaker.i + " Buzz");
                    FizzBuzzMaker.i++;
                    lock.notifyAll();
                } else {
                    try { lock.wait(); } catch (InterruptedException _) {}
                }
            }
        }
    }
}

class FizzBuzz implements Runnable { // 3, 5
    private final Object lock;
    public FizzBuzz(Object lock) { this.lock = lock; }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (FizzBuzzMaker.i > FizzBuzzMaker.n) break;
                if (FizzBuzzMaker.i % 3 == 0 && FizzBuzzMaker.i % 5 == 0) {
                    System.out.println(FizzBuzzMaker.i + " FizzBuzz");
                    FizzBuzzMaker.i++;
                    lock.notifyAll();
                } else {
                    try { lock.wait(); } catch (InterruptedException _) {}
                }
            }
        }
    }
}

class Plain implements Runnable { // !3, !5
    private final Object lock;
    public Plain(Object lock) { this.lock = lock; }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (FizzBuzzMaker.i > FizzBuzzMaker.n) break;
                if (FizzBuzzMaker.i % 3 != 0 && FizzBuzzMaker.i % 5 != 0) {
                    System.out.println(FizzBuzzMaker.i + " Plain");
                    FizzBuzzMaker.i++;
                    lock.notifyAll();
                } else {
                    try { lock.wait(); } catch (InterruptedException e) {}
                }
            }
        }
    }
}
