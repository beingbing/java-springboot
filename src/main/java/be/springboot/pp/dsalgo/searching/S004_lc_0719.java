package be.springboot.pp.dsalgo.searching;

import java.util.Arrays;

public class S004_lc_0719 {
    public int smallestDistancePair(int[] nums, int k) {
        // Sort the array to facilitate pair counting by distance
        Arrays.sort(nums);

        // Define binary search range on the possible distances
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];

        // Binary search on the distance
        while (low < high) {
            int mid = low + (high - low) / 2;

            // Count pairs with distance <= mid
            if (countPairs(nums, mid) >= k) high = mid;
            else low = mid + 1;
        }

        // When binary search completes, low (or high) contains the k-th smallest distance
        return low;
    }

    // Helper function to count pairs with distance <= target
    private int countPairs(int[] nums, int target) {
        int count = 0;
        int left = 0;

        // For each right pointer, find the valid left pointer for distance <= target
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > target) left++;
            count += right - left;
        }

        return count;
    }
}