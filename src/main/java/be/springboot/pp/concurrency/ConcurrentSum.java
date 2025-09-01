package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
// 2
/*
* This program was written to teach you how multiple threads can execute concurrently
* and access shared resources.
* */

public class ConcurrentSum {

    // public static long sum = 0; // will give wrong answer for concurrent sum
    // this is because of contention in accessing sum variable.
    public static AtomicLong sum = new AtomicLong(0);

    public static void main(String[] args) {
        System.out.println("ConcurrentSum: main: args: " + Arrays.toString(args) + " " + Thread.currentThread().getName());

        try {
            List<Integer> nums = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < 1_00_00_000; i++)
                nums.add(random.nextInt(1_00_00_000));

            long start = System.currentTimeMillis();
            seqSum(nums);
            long end = System.currentTimeMillis();
            System.out.println("ConcurrentSum: main: seqSum: time: " + (end - start) + " " + Thread.currentThread().getName());

            long start2 = System.currentTimeMillis();
            concurrentSum(nums, 4);
            long end2 = System.currentTimeMillis();
            System.out.println("ConcurrentSum: main: concurrentSum: time: " + (end2 - start2) + " " + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println("ConcurrentSum: main: exception: " + e.getMessage() + " " + Thread.currentThread().getName());
        }

        System.out.println("ConcurrentSum: main: ends " + Thread.currentThread().getName());
    }

    private static void seqSum(List<Integer> nums) {
        long ans = 0;
        for (Integer num : nums) {
            ans += num;
        }
        System.out.println("ConcurrentSum: seqSum: ans: " + ans + " " + Thread.currentThread().getName());
    }

    private static void concurrentSum(List<Integer> nums, int numThreads) throws InterruptedException {
        int size = nums.size() / numThreads;
        Thread[] workers = new Thread[numThreads];

        sum.set(0); // reset sum before concurrent run

        for (int i = 0; i < numThreads; i++) {
            int left = i * size;
            int right = (i == numThreads - 1) ? nums.size() - 1 : (left + size - 1);
            workers[i] = new Thread(new Worker(nums, left, right));
            workers[i].start();
        }

        for (Thread worker : workers) worker.join();
        System.out.println("ConcurrentSum: concurrentSum: sum: " + sum.get() + " " + Thread.currentThread().getName());
    }
}

class Worker implements Runnable {
    private final List<Integer> nums;
    private final int l, r;

    public Worker(List<Integer> nums, int l, int r) {
        this.nums = nums;
        this.l = l;
        this.r = r;
    }

    @Override
    public void run() {
        long localSum = 0;
        for (int i = l; i <= r; i++) {
            localSum += nums.get(i);
            // ⚠ If we instead did:
            // ConcurrentSum.sum.addAndGet(nums.get(i));
            // we'd create massive contention on AtomicLong and performance would tank.
        }
        ConcurrentSum.sum.addAndGet(localSum);
    }
}
