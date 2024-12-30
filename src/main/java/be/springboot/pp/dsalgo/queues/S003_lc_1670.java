package be.springboot.pp.dsalgo.queues;

import java.util.LinkedList;

// front-middle-back queue
class S003_lc_1670 {
    private final LinkedList<Integer> left;  // Stores the first half of the queue
    private final LinkedList<Integer> right; // Stores the second half of the queue

    // Constructor to initialize the queue
    public S003_lc_1670() {
        left = new LinkedList<>();
        right = new LinkedList<>();
    }

    // Add an element to the front of the queue
    public void pushFront(int val) {
        left.addFirst(val); // Add to the front of the left list
        rebalance(); // Ensure balance between the two lists
    }

    // Add an element to the middle of the queue
    public void pushMiddle(int val) {
        if (left.size() > right.size()) {
            // If left is larger, add the new element to the start of the right list
            right.addFirst(left.removeLast()); // Move the middle to the right
        }
        left.addLast(val); // Add the new middle element to the end of the left list
    }

    // Add an element to the back of the queue
    public void pushBack(int val) {
        right.addLast(val); // Add to the back of the right list
        rebalance(); // Ensure balance between the two lists
    }

    // Remove the front element of the queue
    public int popFront() {
        if (isEmpty()) return -1; // Queue is empty
        int val = !left.isEmpty() ? left.removeFirst() : right.removeFirst(); // Remove from left or right
        rebalance(); // Ensure balance between the two lists
        return val;
    }

    // Remove the middle element of the queue
    public int popMiddle() {
        if (isEmpty()) return -1; // Queue is empty
        int val;
        if (left.size() == right.size()) {
            val = left.removeLast(); // Remove from the end of the left list
        } else {
            val = right.removeFirst(); // Remove from the front of the right list
        }
        return val;
    }

    // Remove the back element of the queue
    public int popBack() {
        if (isEmpty()) return -1; // Queue is empty
        int val = !right.isEmpty() ? right.removeLast() : left.removeLast(); // Remove from right or left
        rebalance(); // Ensure balance between the two lists
        return val;
    }

    // Helper method to rebalance the two lists
    private void rebalance() {
        // Ensure left has at most one more element than right
        if (left.size() > right.size() + 1) {
            right.addFirst(left.removeLast()); // Move from left to right
        } else if (right.size() > left.size()) {
            left.addLast(right.removeFirst()); // Move from right to left
        }
    }

    // Check if the queue is empty
    private boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }
}
