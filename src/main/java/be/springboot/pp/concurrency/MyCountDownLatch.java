package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.List;

public class MyCountDownLatch {
    private int count;

    public MyCountDownLatch(int cnt) {
        this.count = cnt;
    }

    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            System.out.println(Thread.currentThread().getId() + " is waiting");
            wait();
        }
        System.out.println(Thread.currentThread().getId() + " completed waiting period");
    }

    public synchronized void countDown() {
        count--;
        if (count == 0) notify();
        System.out.println(Thread.currentThread().getId() + " decrements the count to: " + count);
    }
}

class OverallSum {
    private int val;

    public OverallSum() {
        this.val = 0;
    }

    public synchronized void add(int x) {
        this.val += x;
    }

    public synchronized int get() {
        return this.val;
    }
}

class Adder implements Runnable {
    private final int s, e;
    private final List<Integer> nums;
    private final MyCountDownLatch countDownLatch;
    private final OverallSum overallSum;
    private final MyCountDownLatch getSetGo;

    Adder(int s, int e, List<Integer> nums, MyCountDownLatch countDownLatch, OverallSum overallSum, MyCountDownLatch getSetGo) {
        this.s = s;
        this.e = e;
        this.nums = nums;
        this.countDownLatch = countDownLatch;
        this.overallSum = overallSum;
        this.getSetGo = getSetGo;
    }

    public void run() {
        try {
            getSetGo.await();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        int a = 0;
        for (int i = s; i <= e; i++) a += nums.get(i);
        overallSum.add(a);
        countDownLatch.countDown();
    }
}

class Getter implements Runnable {
    private final OverallSum overallSum;
    private final MyCountDownLatch countDownLatch;

    Getter(OverallSum overallSum, MyCountDownLatch countDownLatch) {
        this.overallSum = overallSum;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            countDownLatch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sum: " + overallSum.get());
    }
}

class LatchTester {
    public static void main(String[] args) {
        OverallSum overallSum = new OverallSum();
        MyCountDownLatch countDownLatch = new MyCountDownLatch(4);
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 100; i++) nums.add(i);
        int size = 25;
        Thread g = new Thread(new Getter(overallSum, countDownLatch));
        MyCountDownLatch getSetGo = new MyCountDownLatch(1);
        Thread a1 = new Thread(new Adder(0, size - 1, nums, countDownLatch, overallSum, getSetGo));
        Thread a2 = new Thread(new Adder(size, 2*size - 1, nums, countDownLatch, overallSum, getSetGo));
        Thread a3 = new Thread(new Adder(2*size, 3*size - 1, nums, countDownLatch, overallSum, getSetGo));
        Thread a4 = new Thread(new Adder(3*size, 4*size - 1, nums, countDownLatch, overallSum, getSetGo));
        g.start();
        a1.start();
        a2.start();
        a3.start();
        a4.start();
        getSetGo.countDown();
        /*
        * here first a1 is starting then s2 and goes on ...
        * What if we want to start all of them together ?
        * Then, we can use a latch. Set it to 1 and make getter set it to o and all a1, a2, a3 and a4
        * wait on it. Once it is free, all of them will be triggered together.
        * */
    }
}