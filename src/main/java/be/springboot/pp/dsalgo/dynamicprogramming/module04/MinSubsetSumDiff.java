package be.springboot.pp.dsalgo.dynamicprogramming.module04;

import java.util.Arrays;

public class MinSubsetSumDiff {
    public static int minDifference(int[] a) {
        int totalSum = Arrays.stream(a).sum();
        int target = totalSum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: sum 0 is always possible

        for (int num : a)
            for (int j = target; j >= num; j--)
                dp[j] = dp[j] || dp[j - num];

        // Find the largest `s1` that is possible
        int s1 = 0;
        for (int j = target; j >= 0; j--) {
            if (dp[j]) {
                s1 = j;
                break;
            }
        }

        return Math.abs(totalSum - 2 * s1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 11, 5};
        System.out.println(minDifference(arr)); // Output: 1
    }
}