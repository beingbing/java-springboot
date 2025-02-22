package be.springboot.pp.dsalgo.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class S008_cf_846d {
    private static boolean hasBrokenSquare(int[][] prefixSum, int n, int m, int k) {
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                int totalBroken = prefixSum[i][j]
                        - prefixSum[i - k][j]
                        - prefixSum[i][j - k]
                        + prefixSum[i - k][j - k];

                if (totalBroken == k * k) return true; // Found a fully broken k×k square
            }
        }
        return false;
    }

    private static int[][] buildPrefixSum(int[][] grid, int n, int m) {
        int[][] prefixSum = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefixSum[i][j] = grid[i][j]
                        + prefixSum[i - 1][j]
                        + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1];
            }
        }
        return prefixSum;
    }

    private static void applyBrokenPixels(int[][] grid, List<int[]> brokenPixels, int maxIndex) {
        for (int[] row : grid) Arrays.fill(row, 0); // Reset grid

        for (int i = 0; i <= maxIndex; i++) {
            int x = brokenPixels.get(i)[0];
            int y = brokenPixels.get(i)[1];
            grid[x][y] = 1;
        }
    }

    private static int monitorBreakInstant(int gridLen, int gridWid, int damageSize, List<int[]> brokenPixels, int brokenPixelCount) {
        brokenPixels.sort(Comparator.comparingInt(p -> p[2]));

        int[][] grid = new int[gridLen + 1][gridWid + 1];
        int left = 0, right = brokenPixelCount - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            applyBrokenPixels(grid, brokenPixels, mid);
            int[][] prefixSum = buildPrefixSum(grid, gridLen, gridWid);

            if (hasBrokenSquare(prefixSum, gridLen, gridWid, damageSize)) right = mid - 1;
            else left = mid + 1;
        }

        return (left < brokenPixelCount) ? brokenPixels.get(left)[2] : -1;
    }
}
