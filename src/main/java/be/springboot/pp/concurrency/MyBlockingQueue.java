package be.springboot.pp.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MyBlockingQueue {
    private final Queue<Integer> q;
    private final int capacity;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.q = new LinkedList<>();
    }

    public synchronized int take() throws InterruptedException {
        while (q.isEmpty()) wait();
        if (q.size() == capacity) notifyAll();
        return q.poll();
    }

    public synchronized void put(int x) throws InterruptedException {
        while (q.size() == capacity) wait();
        q.add(x);
        if (q.size() == 1) notifyAll();
    }
}

class MyBlockingQueueTester {
    public static void main(String[] args) {
        MyBlockingQueue blockingQueue = new MyBlockingQueue(5);
        Thread producer = new Thread(new MyBlockingQueueProducer(blockingQueue));
        Thread consumer = new Thread(new MyBlockingQueueConsumer(blockingQueue));
        consumer.start();
        producer.start();
    }
}

class MyBlockingQueueProducer implements Runnable {
    private final Random random;
    private final MyBlockingQueue blockingQueue;

    MyBlockingQueueProducer(MyBlockingQueue blockingQueue) {
        this.random = new Random();
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        for (int i = 0; i < 25; i++) {
            try {
                int val = random.nextInt(10, 100);
                System.out.println(Thread.currentThread().getId() + " inserting " + val);
                blockingQueue.put(val);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            blockingQueue.put(-1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MyBlockingQueueConsumer implements Runnable {
    private final MyBlockingQueue blockingQueue;

    MyBlockingQueueConsumer(MyBlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while (true) {
            int val;
            try {
                val = blockingQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getId() + " extracted " + val);
            if (val == -1) break;
        }
    }
}