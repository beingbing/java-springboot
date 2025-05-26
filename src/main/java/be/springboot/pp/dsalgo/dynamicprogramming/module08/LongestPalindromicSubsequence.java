package be.springboot.pp.dsalgo.dynamicprogramming.module08;

public class LongestPalindromicSubsequence {

    public int lps(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single letters are palindromes of length 1
        for (int i = 0; i < n; i++) dp[i][i] = 1;

        // Build up dp table from shorter substrings to longer
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = (len == 2) ? 2 : dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    public int longestPalindromeSubsequence(String s) {
        int n = s.length();
        String r = new StringBuilder(s).reverse().toString(); // Step 1: reverse of s

        // Step 2: Create a DP array to store lengths of longest common subsequence
        int[][] dp = new int[n + 1][n + 1];

        // Step 3: Fill the DP table using LCS approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == r.charAt(j - 1)) {
                    // Characters match, include in subsequence
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Characters don't match, take the max of either excluding s[i-1] or revS[j-1]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return dfs(s, 0, n - 1, memo);
    }

    private int dfs(String s, int left, int right, Integer[][] memo) {
        // Base cases
        if (left > right) return 0;          // Empty substring
        if (left == right) return 1;         // Single character palindrome

        if (memo[left][right] != null) return memo[left][right];

        if (s.charAt(left) == s.charAt(right))
            memo[left][right] = dfs(s, left + 1, right - 1, memo) + 2;
        else
            memo[left][right] = Math.max(dfs(s, left + 1, right, memo),
                    dfs(s, left, right - 1, memo));

        return memo[left][right];
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence solution = new LongestPalindromicSubsequence();

        String s1 = "bbbab";
        String s2 = "cbbd";

        System.out.println("LPS Length (\"bbbab\"): " + solution.longestPalindromeSubsequence(s1));  // Output: 4
        System.out.println("LPS Length (\"cbbd\"): " + solution.longestPalindromeSubsequence(s2));  // Output: 2
    }
}
