package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;

public class S002_lc_0454 {
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // HashMap to store sums of nums1 and nums2 and their frequencies
        Map<Integer, Integer> sumMap = new HashMap<>();

        // Compute all possible sums of nums1[i] + nums2[j]
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int sum = num1 + num2;
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0; // To store the total number of valid tuples

        // Compute all possible sums of nums3[k] + nums4[l]
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                int sum = num3 + num4;
                // Check if the negation of this sum exists in the HashMap
                count += sumMap.getOrDefault(-sum, 0);
            }
        }

        return count;
    }
}
