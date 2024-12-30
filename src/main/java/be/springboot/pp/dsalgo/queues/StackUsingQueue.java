package be.springboot.pp.dsalgo.queues;

import java.util.LinkedList;
import java.util.Queue;

class StackUsingQueue {
    private final Queue<Integer> queue;

    // Constructor to initialize the queue
    public StackUsingQueue() {
        queue = new LinkedList<>();
    }

    // Push an element onto the stack
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        // Rotate the queue to make the last added element the front
        for (int i = 0; i < size; i++) queue.offer(queue.poll());
    }

    // Pop the top element from the stack
    public int pop() {
        if (queue.isEmpty()) throw new RuntimeException("Stack Underflow");
        return queue.poll();
    }

    // Peek the top element of the stack
    public int top() {
        if (queue.isEmpty()) throw new RuntimeException("Stack is Empty");
        return queue.peek();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Get the current size of the stack
    public int size() {
        return queue.size();
    }
}
