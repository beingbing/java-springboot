package be.springboot.pp.dsalgo.greedy;

import java.util.PriorityQueue;

public class S001_lc_0703 {
    private PriorityQueue<Integer> minHeap; // Min-heap to store the k largest elements
    private int k; // Number of elements to track

    // Constructor
    public S001_lc_0703(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>(k); // Initialize a min-heap of size k
        for (int num : nums) add(num); // Add elements from the initial stream
    }

    public int add(int val) { // Add elements from the initial stream
        // Add the new number to the heap
        if (minHeap.size() < k) {
            minHeap.offer(val); // Add directly if heap size is less than k
        } else if (val > minHeap.peek()) {
            minHeap.poll(); // Remove the smallest element
            minHeap.offer(val); // Add the new value
        }

        return minHeap.peek(); // The root of the heap is the kth largest element
    }
}
