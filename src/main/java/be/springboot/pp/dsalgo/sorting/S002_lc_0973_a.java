package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class S002_lc_0973_a {
    public int[][] kClosest(int[][] points, int k) {
        // Priority queue (max-heap) to store k closest points
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                Integer.compare(distanceSquared(b), distanceSquared(a))
        );

        // Add points to the heap
        for (int[] point : points) {
            maxHeap.add(point);
            // Keep the heap size at most k
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Extract the k closest points from the heap
        int[][] result = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty())
            result[index++] = maxHeap.poll();

        return result;
    }

    // Helper method to calculate the squared distance
    private int distanceSquared(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
