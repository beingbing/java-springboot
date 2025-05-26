package be.springboot.pp.dsalgo.dynamicprogramming.module05;

import java.util.Arrays;

public class UnboundedKnapsack {
    public static int unboundedKnapsack(int capacity, int[] val, int[] wt) {
        int n = val.length;
        int[] dp = new int[capacity + 1];

        for (int w = 0; w <= capacity; w++) {
            for (int i = 0; i < n; i++) {
                if (wt[i] <= w) {
                    dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
                }
            }
        }

        return dp[capacity];
    }

    public static void main(String[] args) {
        int[] val = {6, 1, 7, 7};
        int[] wt = {1, 3, 4, 5};
        int capacity = 8;
        System.out.println(unboundedKnapsack(capacity, val, wt)); // Output: 48

        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int cap = 8;
        System.out.println(knapsack(weights, values, cap));  // Output: 12
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int[][] memo = new int[weights.length + 1][capacity + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        return knapsackHelper(weights, values, weights.length, capacity, memo);
    }

    private static int knapsackHelper(int[] weights, int[] values, int n, int capacity, int[][] memo) {
        if (n == 0 || capacity == 0) return 0;
        if (memo[n][capacity] != -1) return memo[n][capacity];

        if (weights[n - 1] > capacity) {
            return memo[n][capacity] = knapsackHelper(weights, values, n - 1, capacity, memo);
        } else {
            return memo[n][capacity] = Math.max(
                    knapsackHelper(weights, values, n - 1, capacity, memo), // exclude
                    values[n - 1] + knapsackHelper(weights, values, n, capacity - weights[n - 1], memo) // include
            );
        }
    }
}