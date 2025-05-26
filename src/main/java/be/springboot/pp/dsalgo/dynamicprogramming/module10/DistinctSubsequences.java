package be.springboot.pp.dsalgo.dynamicprogramming.module10;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();

        // dp[i][j]: number of ways to form t[0..j-1] from s[0..i-1]
        int[][] dp = new int[m + 1][n + 1];

        // Empty t can be formed from any prefix of s in exactly one way (by deleting all characters)
        for (int i = 0; i <= m; i++) dp[i][0] = 1;

        // Fill dp table
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s.charAt(i - 1) == t.charAt(j - 1)) // Include or exclude current s character
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else // Skip current s character
                    dp[i][j] = dp[i - 1][j];

        return dp[m][n];
    }

    public int numOfDistinct(String s, String t) {
        return dfs(0, 0, s, t, new Integer[s.length()][t.length()]);
    }

    private int dfs(int i, int j, String s, String t, Integer[][] memo) {
        if (j == t.length()) return 1; // all characters of t matched
        if (i == s.length()) return 0; // s exhausted before t

        if (memo[i][j] != null) return memo[i][j];

        int count = 0;

        if (s.charAt(i) == t.charAt(j)) // Use s[i] or skip it
            count = dfs(i + 1, j + 1, s, t, memo) + dfs(i + 1, j, s, t, memo);
        else count = dfs(i + 1, j, s, t, memo); // Skip s[i]

        memo[i][j] = count;
        return count;
    }
}
