package be.springboot.pp.dsalgo.dynamicprogramming.module10;

public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        Boolean[][] memo = new Boolean[m + 1][n + 1];
        return matchHelper(m, n, s, p, memo);
    }

    private boolean matchHelper(int i, int j, String s, String p, Boolean[][] memo) {
        if (i == 0 && j == 0) return true;         // both strings empty
        if (j == 0) return false;                  // pattern empty, s not
        if (i == 0) {
            // s is empty, p must be all '*'
            for (int k = 0; k < j; k++) if (p.charAt(k) != '*') return false;
            return true;
        }

        if (memo[i][j] != null) return memo[i][j];

        char sc = s.charAt(i - 1);
        char pc = p.charAt(j - 1);

        if (pc == sc || pc == '?') memo[i][j] = matchHelper(i - 1, j - 1, s, p, memo);
        else if (pc == '*') // * matches 0 or more characters
            memo[i][j] = matchHelper(i, j - 1, s, p, memo) || matchHelper(i - 1, j, s, p, memo);
        else memo[i][j] = false;

        return memo[i][j];
    }

    public boolean isAMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true; // Empty string and empty pattern match

        for (int j = 1; j <= n; j++) // Only '*'s can match empty string
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];

        // Fill the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == sc || pc == '?') dp[i][j] = dp[i - 1][j - 1];
                else if (pc == '*') dp[i][j] = dp[i][j - 1] || dp[i - 1][j]; // 0 or more matches
            }
        }

        return dp[m][n];
    }
}
