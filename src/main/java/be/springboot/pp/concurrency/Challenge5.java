package be.springboot.pp.concurrency;

/*
* Given -
* - a shared container of integers
* - multiple threads
* - a shared variable sum initially set to 0
* All thread should pick 1 number from container add it to sum and then dispose it.
* */

/*
* Issue 1: getting wrong answer
* write sum += x in a synchronized block
*
* Issue 2: getting index-out-of-bound exception
* Make pluck() synchronized
* */

import java.util.ArrayList;
import java.util.List;

public class Challenge5 {
    public static int sum = 0;

    public static void main(String[] args) {
        Container container = new Container(); // it will get populated
        Object lock = new Object();

        int numberOfThreads = 10;
        Thread[] workers = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new Thread(new ContainerWorker(container, lock), "Thread-" + i);
            workers[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final Sum = " + sum);
    }
}

class Container {
    private final List<Integer> nums;
    private int cur;

    public Container() {
        this.nums = new ArrayList<>();
        for (int i = 1; i <= 100; i++) this.nums.add(i);
        this.cur = 0;
    }

    public synchronized int pluck() {
        if (cur == nums.size()) return -1;
        int val = nums.get(cur);
        cur++;
        return val;
    }
}

class ContainerWorker implements Runnable {
    private final Container container;
    private final Object lock;

    public ContainerWorker(Container container, Object lock) {
        this.container = container;
        this.lock = lock;
    }

//    @Override
//    public void run() {
//        while (true) {
//            // we applied a hard check, coz pluck() and sum += x didn't need to be both synchronized together
//            // they could be synchronized separately. We are doing a performance degradation.
//            synchronized(lock) {
//                int x = container.pluck();
//                if (x == -1) break;
//                Challenge5.sum += x;
//            }
//        }
//    }

    @Override
    public void run() {
        while (true) {
            int x = container.pluck(); // separately synchronize it
            if (x == -1) break;
            synchronized(lock) {
                Challenge5.sum += x;
                System.out.println(Thread.currentThread().getName() + " processed " + x + ", sum now: " + Challenge5.sum);
            }
        }
    }
}
// Independent Critical Sections should be guarded independently.