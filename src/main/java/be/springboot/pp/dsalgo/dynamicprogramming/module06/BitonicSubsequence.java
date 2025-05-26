package be.springboot.pp.dsalgo.dynamicprogramming.module06;

import java.util.Arrays;

public class BitonicSubsequence {
    public static int longestBitonicSubsequence(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0; // A valid bitonic sequence must have at least 3 elements

        int[] inc = new int[n]; // Longest Increasing Subsequence (LIS)
        int[] dec = new int[n]; // Longest Decreasing Subsequence (LDS)

        Arrays.fill(inc, 1);
        Arrays.fill(dec, 1);

        // Compute LIS for each index
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    inc[i] = Math.max(inc[i], inc[j] + 1);

        // Compute LDS for each index
        for (int i = n - 2; i >= 0; i--)
            for (int j = i + 1; j < n; j++)
                if (nums[i] > nums[j])
                    dec[i] = Math.max(dec[i], dec[j] + 1);

        // Find the max length of bitonic subsequence
        int maxLen = 0;
        for (int i = 0; i < n; i++)
            if (inc[i] > 1 && dec[i] > 1) // Ensure a valid bitonic sequence
                maxLen = Math.max(maxLen, inc[i] + dec[i] - 1);

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 3, 2};
        int[] nums2 = {1, 11, 2, 10, 4, 5, 2, 1};
        int[] nums3 = {10, 20, 30};
        int[] nums4 = {10, 10, 10};

        System.out.println(longestBitonicSubsequence(nums1)); // Output: 5
        System.out.println(longestBitonicSubsequence(nums2)); // Output: 6
        System.out.println(longestBitonicSubsequence(nums3)); // Output: 0
        System.out.println(longestBitonicSubsequence(nums4)); // Output: 0
    }
}