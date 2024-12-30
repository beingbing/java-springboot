package be.springboot.pp.dsalgo.hashing;

import java.util.Map;
import java.util.HashMap;

public class S001_lc_0974 {
    public static int subarraysDivByK(int[] nums, int k) {
        // HashMap to store the frequency of remainders
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); // Base case: subarray starting at the beginning

        int prefixSum = 0; // Tracks cumulative sum
        int count = 0;     // Tracks the number of subarrays divisible by k

        for (int num : nums) {
            prefixSum += num; // Update the prefix sum

            // Compute the remainder and ensure it's non-negative
            int remainder = prefixSum % k;
            if (remainder < 0) remainder += k;

            // If the remainder exists, add its frequency to the count
            count += remainderCount.getOrDefault(remainder, 0);

            // Update the frequency of the current remainder
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return count; // Return the total count of subarrays
    }
}
