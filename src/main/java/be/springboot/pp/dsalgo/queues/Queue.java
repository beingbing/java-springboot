package be.springboot.pp.dsalgo.queues;

class Queue {
    private final int[] arr; // Array to hold queue elements
    private final int size;  // Maximum size of the queue
    private int front; // Index of the front element
    private int rear;  // Index of the rear element
    private int count; // Current number of elements in the queue

    // Constructor to initialize the queue
    public Queue(int n) {
        size = n;
        arr = new int[n];
        front = 0;
        rear = -1;
        count = 0;
    }

    // Add an element to the queue
    public void enqueue(int x) {
        if (isFull()) throw new RuntimeException("Queue Overflow");
        rear = (rear + 1) % size; // Circular increment
        arr[rear] = x;
        count++;
    }

    // Remove an element from the queue
    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue Underflow");
        int x = arr[front];
        front = (front + 1) % size; // Circular increment
        count--;
        return x;
    }

    // Peek the front element of the queue
    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is Empty");
        return arr[front];
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return count == size;
    }

    // Get the current size of the queue
    public int size() {
        return count;
    }
}
