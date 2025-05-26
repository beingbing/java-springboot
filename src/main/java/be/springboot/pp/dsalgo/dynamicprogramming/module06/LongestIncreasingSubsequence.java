package be.springboot.pp.dsalgo.dynamicprogramming.module06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

    // brute force
    public static int lenOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Every element is a subsequence of length 1

        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);

            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        List<Integer> sub = new ArrayList<>();

        for (int num : nums) {
            int idx = lowerBound(sub, num); // Find the lower bound index

            if (idx == sub.size()) sub.add(num); // Extend the subsequence
            else sub.set(idx, num); // Replace with a smaller element
        }

        return sub.size(); // The length of `sub` gives the LIS length
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= target) right = mid; // Find first element ≥ target
            else left = mid + 1;
        }
        return left; // Position to replace or extend
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lis.lengthOfLIS(nums)); // Output: 4
    }
}