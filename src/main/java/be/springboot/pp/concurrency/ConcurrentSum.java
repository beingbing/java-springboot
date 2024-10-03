package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
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

    public static AtomicInteger cnt = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("ConcurrentSum: main: args: " + args + " " + Thread.currentThread().getName());

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
            concurrentSum(nums);
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

    private static void concurrentSum(List<Integer> nums) throws  InterruptedException {
        int size = nums.size()/4;

        Thread t1 = new Thread(new Worker(nums, 0, size-1));
        Thread t2 = new Thread(new Worker(nums, size, size*2-1));
        Thread t3 = new Thread(new Worker(nums, size*2, size*3-1));
        Thread t4 = new Thread(new Worker(nums, size*3, size*4-1));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        while (cnt.get() < 4) {} // busy waiting
//        System.out.println("ConcurrentSum: concurrentSum: sum: " + sum.get() + " " + Thread.currentThread().getName());
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
//        System.out.println("Worker: run: l: " + l + " r: " + r + " " + Thread.currentThread().getName());
        long s = 0;
        for (int i = l; i <= r; i++) {
            s += nums.get(i);
//            ConcurrentSum.sum.addAndGet(nums.get(i)); // if we do this instead of above line then
            // concurrent sum will have worse performance as compared to sequential sum because of contention.
        }
//        System.out.println("Worker: run: s: " + s + " " + Thread.currentThread().getName());
        long sum = ConcurrentSum.sum.addAndGet(s);
//        System.out.println("Worker: run: sum: " + sum + " " + Thread.currentThread().getName());
        int cnt = ConcurrentSum.cnt.incrementAndGet();
//        System.out.println("Worker: run: cnt: " + cnt + " " + Thread.currentThread().getName());
    }
}