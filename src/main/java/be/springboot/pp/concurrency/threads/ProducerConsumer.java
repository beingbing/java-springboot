package be.springboot.pp.concurrency.threads;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    public static final int NUM_PRODUCERS = 2;
    public static final int NUM_CONSUMERS = 5;

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(50);

        for (int i = 0; i < NUM_PRODUCERS; i++) {
            new Thread(new Producer(queue)).start();
        }

        for (int i = 0; i < NUM_CONSUMERS; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }
}

class MyQueue {
    private final Queue<Integer> list = new LinkedList<>();
    private final int capacity;
    private int activeProducers = ProducerConsumer.NUM_PRODUCERS;

    public MyQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void push(int x) throws InterruptedException {
        while (list.size() == capacity) wait();
        list.offer(x);
        notifyAll();
    }

    public synchronized int pop() throws InterruptedException {
        while (list.isEmpty()) {
            if (activeProducers == 0) return -1;
            wait();
        }
        int val = list.poll();
        notifyAll();
        return val;
    }

    public synchronized void producerDone() {
        activeProducers--;
        notifyAll(); // wake waiting consumers
    }

    public synchronized boolean isProductionOver() {
        return activeProducers == 0 && list.isEmpty();
    }
}

class Producer implements Runnable {
    private final MyQueue queue;

    public Producer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 100; i++) {
                queue.push(i);
                System.out.println("Producer pushed: " + i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            queue.producerDone();
        }
    }
}

class Consumer implements Runnable {
    private final MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int val = queue.pop();
                if (val == -1) break;
                System.out.println("Consumer popped: " + val);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
