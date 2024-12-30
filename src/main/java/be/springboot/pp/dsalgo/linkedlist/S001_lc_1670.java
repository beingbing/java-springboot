package be.springboot.pp.dsalgo.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

class S001_lc_1670 {
    Deque<Integer> leftDeque;   // Left half of the queue
    Deque<Integer> rightDeque;  // Right half of the queue

    public S001_lc_1670() {
        leftDeque = new ArrayDeque<>();
        rightDeque = new ArrayDeque<>();
    }

    // Push to the front of the queue
    public void pushFront(int val) {
        leftDeque.addFirst(val);
        rebalance();
    }

    // Push to the middle of the queue
    public void pushMiddle(int val) {
        leftDeque.addLast(val);
        rebalance();
    }

    // Push to the back of the queue
    public void pushBack(int val) {
        rightDeque.addLast(val);
        rebalance();
    }

    // Pop the front element
    public int popFront() {
        if (isEmpty()) return -1;

        int val = leftDeque.isEmpty() ? rightDeque.pollFirst() : leftDeque.pollFirst();
        rebalance();
        return val;
    }

    // Pop the middle element
    public int popMiddle() {
        if (isEmpty()) return -1;

        int val = leftDeque.pollLast();
        rebalance();
        return val;
    }

    // Pop the back element
    public int popBack() {
        if (isEmpty()) return -1;

        int val = rightDeque.isEmpty() ? leftDeque.pollLast() : rightDeque.pollLast();
        rebalance();
        return val;
    }

    // Check if the queue is empty
    private boolean isEmpty() {
        return leftDeque.isEmpty() && rightDeque.isEmpty();
    }

    // Rebalance the two deques to maintain size properties
    private void rebalance() {
        while (leftDeque.size() > rightDeque.size() + 1) {
            rightDeque.addFirst(leftDeque.pollLast());
        }
        while (leftDeque.size() < rightDeque.size()) {
            leftDeque.addLast(rightDeque.pollFirst());
        }
    }
}
