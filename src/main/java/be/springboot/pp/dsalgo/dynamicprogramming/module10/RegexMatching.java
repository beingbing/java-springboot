package be.springboot.pp.dsalgo.dynamicprogramming.module10;

import java.util.HashMap;
import java.util.Map;

public class RegexMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();

        // dp[i][j] = true if s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // empty matches empty

        // Handle patterns like a*, a*b*, a*b*c* that can match empty string
        for (int j = 2; j <= n; j++)
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2]; // Ignore previous char and '*'

        // Fill the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) // Characters match
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pc == '*') {
                    // '*' can mean zero of previous character
                    dp[i][j] = dp[i][j - 2];

                    // If the char before '*' matches current s
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == sc)
                        dp[i][j] |= dp[i - 1][j]; // consume one character
                }
            }
        }

        return dp[m][n];
    }

    public boolean isAMatch(String s, String p) {
        return dp(0, 0, s, p, new HashMap<>());
    }

    private boolean dp(int i, int j, String s, String p, Map<String, Boolean> memo) {
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);

        if (j == p.length()) return i == s.length(); // both must be exhausted

        boolean firstMatch = (i < s.length()) &&
                (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        boolean ans;

        if ((j + 1) < p.length() && p.charAt(j + 1) == '*') {
            // Two choices: skip 'x*' or use it (if match)
            ans = dp(i, j + 2, s, p, memo) || (firstMatch && dp(i + 1, j, s, p, memo));
        } else ans = firstMatch && dp(i + 1, j + 1, s, p, memo);

        memo.put(key, ans);
        return ans;
    }
}
