package be.springboot.pp.dsalgo.dynamicprogramming.module12;

import java.util.Arrays;

public class UniquePathsObstacle {
    // -------- Top-down
    int[][] memo;

    public int uniquePathsWithObstacle(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        return dfs(m - 1, n - 1, grid);
    }

    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || grid[i][j] == 1) return 0;
        if (i == 0 && j == 0) return 1;

        if (memo[i][j] != -1) return memo[i][j];

        int up = dfs(i - 1, j, grid);
        int left = dfs(i, j - 1, grid);

        return memo[i][j] = up + left;
    }

    // -------- Bottom-Up
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        if (grid[0][0] == 1) return 0; // If starting cell is an obstacle, return 0

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 1; i < m; i++) // Fill first column
            dp[i][0] = (grid[i][0] == 0 && dp[i - 1][0] == 1) ? 1 : 0;

        for (int j = 1; j < n; j++) // Fill first row
            dp[0][j] = (grid[0][j] == 0 && dp[0][j - 1] == 1) ? 1 : 0;

        // Fill rest of the grid
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (grid[i][j] == 0) dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else dp[i][j] = 0; // obstacle

        return dp[m - 1][n - 1];
    }
}
