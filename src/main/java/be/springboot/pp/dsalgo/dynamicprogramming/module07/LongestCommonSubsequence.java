package be.springboot.pp.dsalgo.dynamicprogramming.module07;

import java.util.Arrays;

public class LongestCommonSubsequence {
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // Extend LCS
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Max of excluding one char
                }
            }
        }

        return dp[m][n]; // Final LCS length
    }

    static int[][] memo;

    public static int lcs(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        return dfs(text1, text2, 0, 0);
    }

    private static int dfs(String text1, String text2, int i, int j) {
        if (i == text1.length() || j == text2.length()) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        if (text1.charAt(i) == text2.charAt(j))
            memo[i][j] = 1 + dfs(text1, text2, i + 1, j + 1);
        else
            memo[i][j] = Math.max(dfs(text1, text2, i + 1, j), dfs(text1, text2, i, j + 1));

        return memo[i][j];
    }

    public static int lcsOpt(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // Ensure text1 is the shorter string to minimize space
        if (n < m) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }

        int[] pre = new int[m + 1];
        int[] cur = new int[m + 1];

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (text2.charAt(j - 1) == text1.charAt(i - 1))
                    cur[i] = pre[i - 1] + 1;
                else cur[i] = Math.max(pre[i], cur[i - 1]);
            }

            // Swap prev and curr
            int[] temp = pre;
            pre = cur;
            cur = temp;
        }

        return pre[m];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));  // Output: 3
        System.out.println(longestCommonSubsequence("abc", "abc"));    // Output: 3
        System.out.println(longestCommonSubsequence("abc", "def"));    // Output: 0
        System.out.println(lcs("abcde", "ace"));  // Output: 3
        System.out.println(lcs("abc", "abc"));    // Output: 3
        System.out.println(lcs("abc", "def"));    // Output: 0
    }
}