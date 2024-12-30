package be.springboot.pp.dsalgo.searching;

import java.util.Scanner;

public class S002_lc_0209 {
    private static int findMinSubarrayLength(int[] nums, int target) {
        int n = nums.length;
        int minLength = n + 1;
        int left = 0, currentSum = 0;

        // Sliding window approach
        for (int right = 0; right < n; right++) {
            currentSum += nums[right]; // Expand the window by adding nums[right]

            // Contract the window as long as the current sum is >= target
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1); // Update minimal length
                currentSum -= nums[left]; // Shrink the window from the left
                left++; // Move left pointer rightwards
            }
        }

        // If minLength was updated, return it; otherwise, return 0
        return (minLength == n + 1) ? 0 : minLength;
    }
}
