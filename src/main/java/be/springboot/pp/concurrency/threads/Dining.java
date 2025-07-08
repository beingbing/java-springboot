package be.springboot.pp.concurrency.threads;

public class Dining {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        int eatingRounds = 1;  // Each philosopher eats 3 times

        Chopstick[] chopsticks = new Chopstick[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) chopsticks[i] = new Chopstick(i);

        Thread[] philosophers = new Thread[numPhilosophers];
        String[] names = {"Plato", "Socrates", "Yen", "Horan", "Archie"};

        for (int i = 0; i < numPhilosophers; i++) {
            Chopstick left = chopsticks[i];
            Chopstick right = chopsticks[(i + 1) % numPhilosophers];
            philosophers[i] = new Thread(new Philosopher(left, right, names[i], eatingRounds));
        }

        for (Thread t : philosophers) t.start();
    }
}

class Chopstick {
    private final int id;

    public Chopstick(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Philosopher implements Runnable {
    private final Chopstick left, right;
    private final String name;
    private final int roundsToEat;

    public Philosopher(Chopstick left, Chopstick right, String name, int roundsToEat) {
        this.left = left;
        this.right = right;
        this.name = name;
        this.roundsToEat = roundsToEat;
    }

    @Override
    public void run() {
        int eaten = 0;
        try {
            while (eaten < roundsToEat) {
                think();

                // Lock lower-ID chopstick first to prevent deadlock
                Chopstick first = left.getId() < right.getId() ? left : right;
                Chopstick second = left.getId() < right.getId() ? right : left;

                synchronized (first) { // If the lock is not available, the thread is blocked
                    synchronized (second) { // (i.e., it is moved off the CPU and into the waiting queue).
                        eat(++eaten); // The OS scheduler wakes the thread only when the lock becomes available.
                    }
                }
            }

            System.out.println(name + " has finished eating and leaves the table.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(name + " was interrupted.");
        }
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
