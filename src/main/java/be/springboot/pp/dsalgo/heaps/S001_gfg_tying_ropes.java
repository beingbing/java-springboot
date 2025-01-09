package be.springboot.pp.dsalgo.heaps;

import java.util.PriorityQueue;

public class S001_gfg_tying_ropes {
    public int minCost(int[] a) {
        if (a.length <= 1) return 0; // Base case: If there's only one rope, cost is 0
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min-heap to store the rope lengths
        for (int length : a) minHeap.add(length); // Add all rope lengths to the heap

        int totalCost = 0;
        while (minHeap.size() > 1) { // Combine ropes until one rope remains
            // Extract the two smallest ropes
            int first = minHeap.poll();
            int second = minHeap.poll();
            int cost = first + second; // Calculate the cost to connect them
            totalCost += cost;
            minHeap.add(cost); // Add the resulting rope back into the heap
        }

        return totalCost;
    }
}
