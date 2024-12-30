package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;

public class S003_gfg_larg_sum_0_suba {
    private static int findLargestZeroSumSubarray(int[] arr) {
        // HashMap to store the first occurrence of prefix sums
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            // Case 1: If prefix sum is 0, subarray from 0 to i has sum 0
            if (prefixSum == 0) maxLength = i + 1;

            // Case 2: If prefix sum is already in the map, calculate subarray length
            if (prefixSumMap.containsKey(prefixSum)) {
                int subarrayLength = i - prefixSumMap.get(prefixSum);
                maxLength = Math.max(maxLength, subarrayLength);
            } else {
                // Store the first occurrence of the prefix sum
                prefixSumMap.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}
