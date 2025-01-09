package be.springboot.pp.dsalgo.greedy;

import java.util.PriorityQueue;

public class S002_lc_0295 {
    private final PriorityQueue<Integer> maxHeap; // Max-heap for the left half
    private final PriorityQueue<Integer> minHeap; // Min-heap for the right half

    public S002_lc_0295() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max-heap
        minHeap = new PriorityQueue<>(); // Min-heap
    }

    // Add a number to the data structure
    public void addNum(int num) {
        maxHeap.offer(num); // Add to maxHeap first
        minHeap.offer(maxHeap.poll()); // Ensure maxHeap's max is <= minHeap's min
        if (maxHeap.size() < minHeap.size()) maxHeap.offer(minHeap.poll()); // Balance the heaps
    }

    public double findMedian() { // Find the median of all added numbers
        if (maxHeap.size() == minHeap.size()) return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else return maxHeap.peek();
    }
}
