package be.springboot.pp.dsalgo.dynamicprogramming.module12;

public class MinPathSum {
    // -------- Top-Down
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Integer[][] memo = new Integer[m][n];
        return dfs(grid, m - 1, n - 1, memo);
    }

    private int dfs(int[][] grid, int i, int j, Integer[][] memo) {
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if (i == 0 && j == 0) return grid[0][0];
        if (memo[i][j] != null) return memo[i][j];
        int up = dfs(grid, i - 1, j, memo);
        int left = dfs(grid, i, j - 1, memo);
        memo[i][j] = grid[i][j] + Math.min(up, left);
        return memo[i][j];
    }

    // -------- Bottom-Up
    public int minimumPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0]; // Initialize the starting point

        for (int j = 1; j < n; j++) // Initialize the first row
            dp[0][j] = dp[0][j - 1] + grid[0][j];

        for (int i = 1; i < m; i++) // Initialize the first column
            dp[i][0] = dp[i - 1][0] + grid[i][0];

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);

        return dp[m - 1][n - 1];
    }
}
