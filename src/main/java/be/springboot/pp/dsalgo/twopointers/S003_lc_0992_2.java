package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;
import java.util.Map;

public class S003_lc_0992_2 {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        if (k == n) return 1; // If k equals the array size, there's only one valid subarray.

        // Frequency map to track the count of integers in the current window
        Map<Integer, Integer> freqMap = new HashMap<>();
        int start = 0, distinctCount = 0, totalCount = 0;

        // Iterate over the array with the end pointer
        for (int end = 0; end < n; end++) {
            // Add the current number to the frequency map and update the distinct count
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0) + 1);
            if (freqMap.get(nums[end]) == 1) distinctCount++; // New distinct integer found

            // When the window contains exactly k distinct integers
            while (distinctCount == k) {
                int rightExtension = end;

                // Extend the window to the right while maintaining k distinct integers
                while (rightExtension < n - 1 && freqMap.containsKey(nums[rightExtension + 1])) rightExtension++;

                // Add all valid subarrays from the current window
                totalCount += rightExtension - end + 1;

                // Shrink the window from the left
                if (freqMap.get(nums[start]) == 1) distinctCount--; // Removing a distinct integer
                freqMap.put(nums[start], freqMap.get(nums[start]) - 1);
                if (freqMap.get(nums[start]) == 0) freqMap.remove(nums[start]); // Remove from map if count is 0
                start++;
            }
        }

        return totalCount;
    }
}

class S003_lc_0992_Optimized {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        if (k == n) return 1; // If k equals the array size, there's only one valid subarray.

        // Use an array to track the count of integers in the current window
        int[] freqArray = new int[n + 1]; // Array size is n+1 to directly index numbers 1 to n
        int start = 0, distinctCount = 0, totalCount = 0;

        // Iterate over the array with the end pointer
        for (int end = 0; end < n; end++) {
            // Add the current number to the frequency array and update the distinct count
            if (freqArray[nums[end]] == 0) distinctCount++; // New distinct integer found
            freqArray[nums[end]]++;

            // When the window contains exactly k distinct integers
            while (distinctCount == k) {
                int rightExtension = end;

                // Extend the window to the right while maintaining k distinct integers
                while (rightExtension < n - 1 && freqArray[nums[rightExtension + 1]] > 0) rightExtension++;

                // Add all valid subarrays from the current window
                totalCount += rightExtension - end + 1;

                // Shrink the window from the left
                if (freqArray[nums[start]] == 1) distinctCount--; // Removing a distinct integer
                freqArray[nums[start]]--;
                start++;
            }
        }

        return totalCount;
    }
}
