package be.springboot.pp.dsalgo.dynamicprogramming.module06;

import java.util.Arrays;

public class NumberOfLIS {
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];     // Stores length of LIS ending at each index
        int[] count = new int[n];  // Stores number of LIS ending at each index
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLength = 1, result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // Inherit count from j
                    } else if (dp[j] + 1 == dp[i])
                        count[i] += count[j]; // Additional sequences found

            maxLength = Math.max(maxLength, dp[i]);
        }

        // Count LIS occurrences
        for (int i = 0; i < n; i++)
            if (dp[i] == maxLength)
                result += count[i];

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 4, 7};
        int[] nums2 = {2, 2, 2, 2, 2};

        System.out.println(findNumberOfLIS(nums1)); // Output: 2
        System.out.println(findNumberOfLIS(nums2)); // Output: 5
    }
}