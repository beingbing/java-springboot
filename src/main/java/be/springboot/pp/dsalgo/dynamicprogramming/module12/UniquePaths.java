package be.springboot.pp.dsalgo.dynamicprogramming.module12;

public class UniquePaths {
    // -------- Top-down
    public int uniquePathsTD(int m, int n) {
        int[][] memo = new int[m][n];
        return countPaths(0, 0, m, n, memo);
    }

    private int countPaths(int i, int j, int m, int n, int[][] memo) {
        if (i >= m || j >= n) return 0; // If out of bounds, no path
        if (i == m - 1 && j == n - 1) return 1; // If reached destination
        if (memo[i][j] > 0) return memo[i][j]; // If already computed

        // Move right and down
        memo[i][j] = countPaths(i + 1, j, m, n, memo) + countPaths(i, j + 1, m, n, memo);
        return memo[i][j];
    }

    // -------- Bottom-Up
    public int uniquePathsBU(int m, int n) {
        int[][] dp = new int[m][n];

        // Fill first row and column with 1s (only one way to reach those cells)
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Fill rest of the grid
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) // Sum of top and left
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

        return dp[m - 1][n - 1];
    }

    // -------- Best: Combinatorics: Use combinatorial formula: C(m+n-2, m-1)
    public int uniquePaths(int m, int n) {
        long res = 1;
        int N = m + n - 2;
        int k = Math.min(m - 1, n - 1); // Choose the smaller one for efficiency

        for (int i = 1; i <= k; i++)
            res = res * (N - k + i) / i;

        return (int) res;
    }
}
