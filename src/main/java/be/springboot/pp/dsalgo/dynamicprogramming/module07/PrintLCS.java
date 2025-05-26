package be.springboot.pp.dsalgo.dynamicprogramming.module07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintLCS {
    public static List<Integer> printLCS(List<Integer> a, List<Integer> b) {
        int m = a.size(), n = b.size();
        int[][] dp = new int[m + 1][n + 1];

        // Step 1: Fill the DP table
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (a.get(i - 1).equals(b.get(j - 1))) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

        // Step 2: Reconstruct LCS from DP table
        List<Integer> lcs = new ArrayList<>();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (a.get(i - 1).equals(b.get(j - 1))) { // If characters match, it's part of LCS
                lcs.add(a.get(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) { // Move up if top value is greater
                i--;
            } else { // Move left if left value is greater or equal
                j--;
            }
        }

        // Since we built LCS from end to start, reverse it
        Collections.reverse(lcs);
        return lcs;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 1);
        List<Integer> list2 = Arrays.asList(3, 4, 1, 2, 1, 3);
        List<Integer> lcs = printLCS(list1, list2); // Output: 3 4 1 (or any valid LCS)
        System.out.println(lcs);
    }
}