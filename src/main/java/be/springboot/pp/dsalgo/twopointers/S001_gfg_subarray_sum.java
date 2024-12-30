package be.springboot.pp.dsalgo.twopointers;

import java.util.Scanner;

public class S001_gfg_subarray_sum {
    public static int[] findSubarrayWithSum(int[] a, int n, long sum) {
        int start = 0; // Start of the sliding window
        long currentSum = 0; // Current sum of the window

        // Traverse the array with the end pointer
        for (int end = 0; end < n; end++) {
            // Add the current element to the window
            currentSum += a[end];

            // Shrink the window from the left if the sum exceeds sum
            // also, do not let slow pointer go ahead of fast pointer
            while (currentSum > sum && start < end) {
                currentSum -= a[start];
                start++;
            }

            // Check if we found the target sum
            if (currentSum == sum) return new int[]{start + 1, end + 1}; // Return 1-based indices
        }

        // If no subarray is found, return -1
        return new int[]{-1};
    }
}
