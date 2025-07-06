package be.springboot.pp.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadedSumCalculator {
    // Shared container
    static Queue<Integer> container = new ConcurrentLinkedQueue<>();

    // Shared sum variable
    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) {
        // Populate container
        for (int i = 1; i <= 100; i++) {
            container.add(i); // 1 to 100
        }

        // Number of threads
        int numberOfThreads = 5;
        Thread[] workers = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new Thread(new Worker(), "Thread-" + i);
            workers[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print final result
        System.out.println("Final Sum = " + sum.get());
    }

    static class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                Integer val = container.poll(); // Atomically fetch & remove

                if (val == null) break; // Nothing left to process

                // Add to sum atomically
                int newSum = sum.addAndGet(val);
                System.out.println(Thread.currentThread().getName() + " processed " + val + ", sum now: " + newSum);
            }
        }
    }
}
