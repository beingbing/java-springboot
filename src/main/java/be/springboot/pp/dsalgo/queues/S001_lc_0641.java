package be.springboot.pp.dsalgo.queues;

// circular deque
public class S001_lc_0641 {
    private final int[] deque;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    // Constructor to initialize the deque with max size k
    public S001_lc_0641(int k) {
        deque = new int[k];
        front = 0; // Points to the first element
        rear = k - 1; // Points to the last element
        size = 0;
        capacity = k;
    }

    // Insert an element at the front of the deque
    public boolean insertFront(int value) {
        if (isFull()) return false; // Cannot insert into a full deque
        front = (front - 1 + capacity) % capacity; // Move front pointer backward
        deque[front] = value; // Insert value
        size++;
        return true;
    }

    // Insert an element at the rear of the deque
    public boolean insertLast(int value) {
        if (isFull()) return false; // Cannot insert into a full deque
        rear = (rear + 1) % capacity; // Move rear pointer forward
        deque[rear] = value; // Insert value
        size++;
        return true;
    }

    // Delete an element from the front of the deque
    public boolean deleteFront() {
        if (isEmpty()) return false; // Cannot delete from an empty deque
        front = (front + 1) % capacity; // Move front pointer forward
        size--;
        return true;
    }

    // Delete an element from the rear of the deque
    public boolean deleteLast() {
        if (isEmpty()) return false; // Cannot delete from an empty deque
        rear = (rear - 1 + capacity) % capacity; // Move rear pointer backward
        size--;
        return true;
    }

    // Get the front element of the deque
    public int getFront() {
        if (isEmpty()) return -1; // Return -1 if deque is empty
        return deque[front];
    }

    // Get the rear element of the deque
    public int getRear() {
        if (isEmpty()) return -1; // Return -1 if deque is empty
        return deque[rear];
    }

    // Check if the deque is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the deque is full
    public boolean isFull() {
        return size == capacity;
    }
}
