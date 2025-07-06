package be.springboot.pp.concurrency;

/*
* You have 2 shared variables, cur (starting from 1) and limit, one thread should print all even
* number and other all odd number in given range.
* */

/*
* 2. Challenge3.cur <= Challenge3.limit is leading to race condition.
* After evaluating this condition, a thread enters and then gets switched, so
* when respawn that thread directly goes to evaluate second condition. This is
* where things went wrong. So fix it we will acquire a lock.
* */

public class Challenge3 {
    public static final int limit = 2_00_000;
// 1.    public static int cur = 1; // it had memory visibility issue
    public static int cur = 1; // we can remove volatile from here now, as we acquire lock due to 2nd issue.

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new EvenWorker("even", lock));
        Thread t2 = new Thread(new OddWorker("Odd", lock));
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("Challenge3 took " + (end - start) + " ms to complete.");
    }
}

class EvenWorker implements Runnable {
    private final String name;
    private final Object lock;

    public EvenWorker(String name, Object lock) {
        this.name = name;
        this.lock = lock;
    }

//    @Override
//    public void run() {
//        while (Challenge3.cur <= Challenge3.limit) {
//            if (Challenge3.cur %2 == 0) {
//                System.out.println(name + " " + Challenge3.cur);
//                Challenge3.cur++;
//            }
//        }
//    }

    @Override
    public void run() {
        while (true) {
            synchronized(lock) {
                if (Challenge3.cur > Challenge3.limit) break; // we put it inside while coz otherwise we would stuck in an infinite loop
                if (Challenge3.cur %2 == 0) {
                    System.out.println(name + " " + Challenge3.cur);
                    Challenge3.cur++;
                }
            }
        }
    }
}

class OddWorker implements Runnable {
    private final String name;
    private final Object lock;

    public OddWorker(String name, Object lock) {
        this.name = name;
        this.lock = lock;
    }

//    @Override
//    public void run() {
//        while (Challenge3.cur <= Challenge3.limit) {
//            if (Challenge3.cur % 2 != 0) {
//                System.out.println(name + " " + Challenge3.cur);
//                Challenge3.cur++;
//            }
//        }
//    }

    @Override
    public void run() {
        while (true) {
            synchronized(lock) {
                if (Challenge3.cur > Challenge3.limit) break;
                if (Challenge3.cur % 2 != 0) {
                    System.out.println(name + " " + Challenge3.cur);
                    Challenge3.cur++;
                }
            }
        }
    }
}

// Compound actions should be guarded inside a lock