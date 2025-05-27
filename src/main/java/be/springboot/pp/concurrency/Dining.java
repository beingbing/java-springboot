package be.springboot.pp.concurrency;

public class Dining {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick(1);
        Chopstick c2 = new Chopstick(2);
        Chopstick c3 = new Chopstick(3);
        Chopstick c4 = new Chopstick(4);
        Chopstick c5 = new Chopstick(5);
        Thread p1 = new Thread(new Philosopher(c1, c2, "Plato"));
        Thread p2 = new Thread(new Philosopher(c2, c3, "Socrates"));
        Thread p3 = new Thread(new Philosopher(c3, c4, "Yen"));
        Thread p4 = new Thread(new Philosopher(c4, c5, "Horan"));
        Thread p5 = new Thread(new Philosopher(c5, c1, "Archie"));
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}

class Chopstick {
    private final int id;

    Chopstick(int id) {
        this.id = id;
    }
}

class Philosopher implements Runnable {
    private final Chopstick left, right;
    private final String name;

    Philosopher(Chopstick left, Chopstick right, String name) {
        this.left = left;
        this.right = right;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(name + " is thinking...");
                Thread.sleep(2000);
                synchronized(left) {
                    Thread.sleep(200);
                    synchronized(right) {
                        System.out.println(name + " is eating.... :)");
                        Thread.sleep(2000);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}