package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;

public class S004_gfg_long_sum_k_suba {
    private static int findLongestSubarrayWithSumK(int[] arr, int K) {
        // HashMap to store the first occurrence of each prefix sum
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        long prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            // Case 1: If the prefix sum itself is equal to K
            if (prefixSum == K) maxLength = i + 1;

            // Case 2: Check if (prefixSum - K) exists in the map
            if (prefixSumMap.containsKey(prefixSum - K)) {
                int subarrayLength = i - prefixSumMap.get(prefixSum - K);
                maxLength = Math.max(maxLength, subarrayLength);
            }

            // Case 3: Store the prefix sum in the map if it's not already there
            if (!prefixSumMap.containsKey(prefixSum)) {
                prefixSumMap.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}
