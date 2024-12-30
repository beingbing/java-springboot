package be.springboot.pp.dsalgo.searching;

import java.util.Scanner;

public class S007_cf_670d1 {
    // Function to check if x cookies can be baked with available ingredients and magic powder
    private static boolean canBake(int[] wtNeeded, int[] wtPresent, int ingredientsCnt, long xtra, long x) {
        long totalShortage = 0;

        for (int i = 0; i < ingredientsCnt; i++) {
            long required = x * wtNeeded[i];
            long shortage = Math.max(0, required - wtPresent[i]);
            totalShortage += shortage;

            if (totalShortage > xtra) return false; // Can't bake x cookies if shortage exceeds available powder
        }

        return true;
    }

    private static long maxCookies(int[] wtNeeded, int[] wtPresent, int ingredientsCnt, long xtra) {
        long left = 0, right = Integer.MIN_VALUE, maxCookies = 0;

        for (int wt : wtPresent) right = Math.max(wt, right);
        right += xtra;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canBake(wtNeeded, wtPresent, ingredientsCnt, xtra, mid)) {
                maxCookies = mid; // Mid is feasible, try for more cookies
                left = mid + 1;
            } else right = mid - 1; // Reduce the range
        }

        return maxCookies;
    }
}
