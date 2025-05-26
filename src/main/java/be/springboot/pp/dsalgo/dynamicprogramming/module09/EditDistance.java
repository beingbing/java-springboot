package be.springboot.pp.dsalgo.dynamicprogramming.module09;

import java.util.Arrays;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        // dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Initialize base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i; // delete all
        for (int j = 0; j <= n; j++) dp[0][j] = j; // insert all

        // Build DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // No operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Choose the best among insert, delete, replace
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],           // delete
                            Math.min(dp[i][j - 1],  // insert
                                    dp[i - 1][j - 1]) // replace
                    );
                }
            }
        }

        return dp[m][n];
    }

    public int minDistances(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        return dp(word1, word2, m - 1, n - 1, memo);
    }

    private int dp(String w1, String w2, int i, int j, int[][] memo) {
        if (i < 0) return j + 1; // insert all characters of w2[0..j]
        if (j < 0) return i + 1; // delete all characters of w1[0..i]

        if (memo[i][j] != -1) return memo[i][j];

        if (w1.charAt(i) == w2.charAt(j)) // No edit required
            return memo[i][j] = dp(w1, w2, i - 1, j - 1, memo);

        // Try insert, delete, and replace
        int insert = dp(w1, w2, i, j - 1, memo);
        int delete = dp(w1, w2, i - 1, j, memo);
        int replace = dp(w1, w2, i - 1, j - 1, memo);

        return memo[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
    }
}
