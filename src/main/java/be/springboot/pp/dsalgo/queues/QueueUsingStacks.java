package be.springboot.pp.dsalgo.queues;

import java.util.Stack;

public class QueueUsingStacks {
    private final Stack<Integer> input; // Stack for enqueue operation
    private final Stack<Integer> output; // Stack for dequeue operation

    // Constructor to initialize the stacks
    public QueueUsingStacks() {
        input = new Stack<>();
        output = new Stack<>();
    }

    // Add an element to the queue
    public void enqueue(int x) {
        input.push(x);
    }

    // Remove an element from the queue
    public int dequeue() {
        if (output.isEmpty()) {
            if (input.isEmpty()) throw new RuntimeException("Queue Underflow");

            // Move all elements from input stack to output stack
            while (!input.isEmpty()) output.push(input.pop());
        }
        return output.pop();
    }

    // Peek the front element of the queue
    public int peek() {
        if (output.isEmpty()) {
            if (input.isEmpty()) throw new RuntimeException("Queue is Empty");

            // Move all elements from input to output stack
            while (!input.isEmpty()) output.push(input.pop());
        }
        return output.peek();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return input.isEmpty() && output.isEmpty();
    }

    // Get the current size of the queue
    public int size() {
        return input.size() + output.size();
    }
}
