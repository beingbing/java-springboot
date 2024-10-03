package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.List;
// 17
public class ProducerConsumer {

    public static int producerCount = 2;

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(50);
        Thread producer1 = new Thread(new Producer(queue));
        Thread producer2 = new Thread(new Producer(queue));
        Thread consumer1 = new Thread(new Consumer(queue));
        Thread consumer2 = new Thread(new Consumer(queue));
        Thread consumer3 = new Thread(new Consumer(queue));
        Thread consumer4 = new Thread(new Consumer(queue));
        Thread consumer5 = new Thread(new Consumer(queue));

        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
    }
}

class MyQueue {

    private int front, rear;

    private final List<Integer> list;

    private final int capacity;

    public MyQueue(int cap) {
        this.capacity = cap;
        list = new ArrayList<>(capacity);
        front = -1;
        rear = 0;
    }

    public boolean isFull() {
        return front - rear + 1 == capacity;
    }

    public boolean isEmpty() {
        return rear > front;
    }

    public void push(Integer x) {
        if (isFull())
            throw new RuntimeException("OverFlow: Queue is full");

        list.add(x);
        front++;
    }

    public Integer pop() {
        if (isEmpty())
            throw new RuntimeException("UnderFlow: Queue is empty");
        return list.get(rear++);
    }
}

class Producer implements Runnable {

    private final MyQueue queue;

    public Producer(MyQueue queue) {
        this.queue = queue;
    }

    private void pushIfQueueHadCapacity(Integer i) {
        synchronized(queue) {
            while (queue.isFull()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.push(i);
            System.out.println("Producer: pushed: " + i);
            queue.notifyAll();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            pushIfQueueHadCapacity(i);
        }
        pushIfQueueHadCapacity(-1);
    }
}

class Consumer implements Runnable {

    private final MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
    }

    private Integer popIfQueueHadElements() {
        synchronized(queue) {
            while (queue.isEmpty()) {
                if (ProducerConsumer.producerCount == 0) return -1;
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Integer val = queue.pop();
            queue.notifyAll();
            if (val == -1) ProducerConsumer.producerCount--;
            return val;
        }
    }

    @Override
    public void run() {
        while (true) {
            Integer val = popIfQueueHadElements();
            System.out.println("Consumer: popped: " + val);
            if (val == -1)
                break;
        }
    }
}