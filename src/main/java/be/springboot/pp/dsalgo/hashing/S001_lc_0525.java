package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;

public class S001_lc_0525 {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> prefixMap = new HashMap<>(); // Initialize a hash map to store prefix sums and their first occurrences
        prefixMap.put(0, -1); // Handle cases where the subarray starts at index 0

        int maxLength = 0;  // To store the maximum length of a valid subarray
        int prefixSum = 0;  // To store the running prefix sum

        for (int i = 0; i < nums.length; i++) {
            // Replace 0 with -1 in the prefix sum calculation
            prefixSum += (nums[i] == 0 ? -1 : nums[i]);

            if (prefixMap.containsKey(prefixSum)) { // Check if this prefix sum has been seen before
                int length = i - prefixMap.get(prefixSum);
                maxLength = Math.max(maxLength, length);
            } else prefixMap.put(prefixSum, i);
        }

        return maxLength;
    }
}
