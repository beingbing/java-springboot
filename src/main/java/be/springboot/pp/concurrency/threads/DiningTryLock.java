package be.springboot.pp.concurrency.threads;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class DiningTryLock {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        int eatingRounds = 3;

        ChopstickTryLock[] chopsticks = new ChopstickTryLock[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) chopsticks[i] = new ChopstickTryLock(i);

        Thread[] philosophers = new Thread[numPhilosophers];
        String[] names = {"Plato", "Socrates", "Yen", "Horan", "Archie"};

        for (int i = 0; i < numPhilosophers; i++) {
            ChopstickTryLock left = chopsticks[i];
            ChopstickTryLock right = chopsticks[(i + 1) % numPhilosophers];
            philosophers[i] = new Thread(new PhilosopherTryLock(left, right, names[i], eatingRounds));
        }

        for (Thread t : philosophers) t.start();
    }
}

class ChopstickTryLock {
    private final int id;
    private final ReentrantLock lock = new ReentrantLock();

    public ChopstickTryLock(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean pickUp(long timeout, TimeUnit unit) throws InterruptedException {
        return lock.tryLock(timeout, unit);
    }

    public void putDown() {
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}

class PhilosopherTryLock implements Runnable {
    private final ChopstickTryLock left, right;
    private final String name;
    private final int roundsToEat;

    public PhilosopherTryLock(ChopstickTryLock left, ChopstickTryLock right, String name, int roundsToEat) {
        this.left = left;
        this.right = right;
        this.name = name;
        this.roundsToEat = roundsToEat;
    }

    @Override
    public void run() {
        int eaten = 0;

        while (eaten < roundsToEat) {
            try {
                think();

                // Try to pick up both chopsticks with timeout
                if (left.pickUp(500, TimeUnit.MILLISECONDS)) {
                    try {
                        if (right.pickUp(500, TimeUnit.MILLISECONDS)) {
                            try {
                                eat(++eaten);
                            } finally {
                                right.putDown();
                            }
                        } else {
                            System.out.println(name + " couldn't acquire right chopstick. Retrying...");
                        }
                    } finally {
                        left.putDown();
                    }
                } else {
                    System.out.println(name + " couldn't acquire left chopstick. Retrying...");
                }

                // Small pause before retrying
                Thread.sleep((long) (Math.random() * 300 + 200));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(name + " was interrupted.");
                break;
            }
        }

        System.out.println(name + " has finished eating and leaves the table.");
    }

    private void think() throws InterruptedException {
        System.out.println(name + " is thinking...");
        Thread.sleep((long) (Math.random() * 1000 + 500));
    }

    private void eat(int round) throws InterruptedException {
        System.out.println(name + " is eating (round " + round + ")...");
        Thread.sleep((long) (Math.random() * 1000 + 500));
    }
}
