package be.springboot.pp.dsalgo.dynamicprogramming.module05;

public class Knapsack01 {
    public static int knapsack(int W, int[] val, int[] wt) {
        int n = val.length;
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int w = W; w >= wt[i]; w--) {
                dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
            }
        }

        return dp[W];
    }

    public static int knapsack(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            int wt = weights[i - 1];
            int val = values[i - 1];
            for (int w = 0; w <= W; w++) {
                dp[i][w] = dp[i - 1][w]; // Not take

                if (wt <= w) // Take if it fits
                    dp[i][w] = Math.max(dp[i][w], val + dp[i - 1][w - wt]);
            }
        }

        return dp[n][W];
    }
}