package be.springboot.pp.dsalgo.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class S003_lc_0373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Sortedness> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p.value));

        // Initialize the heap with the first element of nums2 with all of nums1
        int n = Math.min(nums1.length, k);
        for (int i = 0; i < n; i++)
            minHeap.offer(new Sortedness(nums1[i] + nums2[0], i, 0 )); // sum, index in nums1, index in nums2

        List<List<Integer>> result = new ArrayList<>();

        while (k > 0 && !minHeap.isEmpty()) {
            Sortedness current = minHeap.poll(); // Smallest pair
            int sum = current.value, i = current.row, j = current.column;
            result.add(new ArrayList<>(Arrays.asList(nums1[i], nums2[j])));

            // Move to the next element in nums2 for the same nums1[i]
            if (j + 1 < nums2.length)
                minHeap.offer(new Sortedness( nums1[i] + nums2[j + 1], i, j + 1 ));

            k--;
        }

        return result;
    }
}
