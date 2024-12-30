package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;

public class S005_lc_0560 {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Base case: a subarray that starts from index 0

        int prefixSum = 0; // Tracks the cumulative sum
        int count = 0;     // Tracks the number of subarrays whose sum equals k

        for (int num : nums) {
            prefixSum += num; // Update the prefix sum

            // Check if prefixSum - k exists in the map
            if (prefixSumMap.containsKey(prefixSum - k)) count += prefixSumMap.get(prefixSum - k);

            // Update the frequency of the current prefixSum in the map
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count; // Return the total count of subarrays
    }
}
