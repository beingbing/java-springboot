package be.springboot.pp.dsalgo.dynamicprogramming.module03;

import java.util.Arrays;

public class MaxCutSegments {
    public int maximizeCuts(int n, int x, int y, int z) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1); // Initialize with -1 (impossible state)
        dp[0] = 0; // Base case: No cuts for length 0

        for (int i = 1; i <= n; i++) {
            if (i >= x && dp[i - x] != -1) dp[i] = Math.max(dp[i], 1 + dp[i - x]);
            if (i >= y && dp[i - y] != -1) dp[i] = Math.max(dp[i], 1 + dp[i - y]);
            if (i >= z && dp[i - z] != -1) dp[i] = Math.max(dp[i], 1 + dp[i - z]);
        }

        return dp[n] == -1 ? 0 : dp[n]; // Return 0 if no valid cuts exist
    }

    public static void main(String[] args) {
        MaxCutSegments mcs = new MaxCutSegments();
        System.out.println(mcs.maximizeCuts(7, 5, 2, 2)); // Output: 3
        System.out.println(mcs.maximizeCuts(4, 2, 1, 1)); // Output: 4
        System.out.println(mcs.maximizeCuts(9, 3, 3, 3)); // Output: 3
    }
}