package be.springboot.pp.dsalgo.dynamicprogramming.module11;

public class MatrixChainMultiplication {
    public int matrixMultiplication(int[] a) {
        int n = a.length;
        Integer[][] memo = new Integer[n][n];
        return solve(a, 1, n - 1, memo);
    }

    private int solve(int[] a, int i, int j, Integer[][] memo) {
        if (i == j) return 0; // one matrix, no multiplication

        if (memo[i][j] != null) return memo[i][j];

        int min = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int left = solve(a, i, k, memo);       // cost from i to k
            int right = solve(a, k + 1, j, memo);  // cost from k+1 to j
            int cost = a[i - 1] * a[k] * a[j]; // multiplying results
            min = Math.min(min, left + right + cost);
        }

        memo[i][j] = min;
        return min;
    }

    public int matMultiplication(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];

        // l is the chain length
        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + a[i - 1] * a[k] * a[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n - 1];
    }
}
