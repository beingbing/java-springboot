package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(50);
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
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

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            queue.push(i);
            System.out.println("Producer: pushed: " + i);
        }
        queue.push(-1);
        System.out.println("Producer: pushed: -1");
    }
}

class Consumer implements Runnable {

    private final MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Integer val = queue.pop();
            System.out.println("Consumer: popped: " + val);
            if (val == -1)
                break;
        }
    }
}