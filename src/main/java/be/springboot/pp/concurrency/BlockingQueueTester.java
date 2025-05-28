package be.springboot.pp.concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTester {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);
        Thread producer = new Thread(new BlockingQueueProducer(blockingQueue));
        Thread consumer = new Thread(new BlockingQueueConsumer(blockingQueue));
        consumer.start();
        producer.start();
    }
}

class BlockingQueueProducer implements Runnable {
    private final Random random;
    private final BlockingQueue<Integer> blockingQueue;

    BlockingQueueProducer(BlockingQueue<Integer> blockingQueue) {
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

class BlockingQueueConsumer implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    BlockingQueueConsumer(BlockingQueue<Integer> blockingQueue) {
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