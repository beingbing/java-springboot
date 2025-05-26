package be.springboot.pp.dsalgo.dynamicprogramming.module08;

import java.util.Arrays;

public class PalindromePartitioning {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] minCuts = new int[n];

        // Precompute palindrome substrings
        for (int end = 0; end < n; end++) {
            minCuts[end] = end;  // max cuts at most = end (worst case all single chars)
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                        (end - start <= 2 || isPalindrome[start + 1][end - 1])) {
                    isPalindrome[start][end] = true;

                    // if whole substring is palindrome, no cut needed before start
                    if (start == 0) minCuts[end] = 0; // whole substring is palindrome
                    else minCuts[end] = Math.min(minCuts[end], minCuts[start - 1] + 1);
                }
            }
        }

        return minCuts[n - 1];
    }

    public int minCuts(String s) {
        int n = s.length();
        int[] memo = new int[n];
        boolean[][] isPalindrome = new boolean[n][n];
        Arrays.fill(memo, -1);

        // Precompute palindrome substrings
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                        (end - start <= 2 || isPalindrome[start + 1][end - 1])) {
                    isPalindrome[start][end] = true;
                }
            }
        }

        return dfs(s, s.length() - 1, memo, isPalindrome);
    }

    private int dfs(String s, int end, int[] memo, boolean[][] isPalindrome) {
        if (isPalindrome[0][end]) return 0;
        if (memo[end] != -1) return memo[end];

        int minCuts = end;
        for (int i = 1; i <= end; i++) {
            if (isPalindrome[i][end]) {
                minCuts = Math.min(minCuts, dfs(s, i - 1, memo, isPalindrome) + 1);
            }
        }

        return memo[end] = minCuts;
    }
}
