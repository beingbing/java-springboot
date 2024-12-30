package be.springboot.pp.dsalgo.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class S008_cf_846d {
    // Check if there exists a k x k square fully broken at time t
    private static boolean isBroken(int[][] grid, int gridLen, int gridWid, int damageSize) {
        int[][] prefixSum = new int[gridLen + 1][gridWid + 1];

        // Compute prefix sum
        for (int i = 1; i <= gridLen; i++) {
            for (int j = 1; j <= gridWid; j++) {
                prefixSum[i][j] = grid[i][j]
                        + prefixSum[i - 1][j]
                        + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1];
            }
        }

        // Check all k x k squares
        for (int i = damageSize; i <= gridLen; i++) {
            for (int j = damageSize; j <= gridWid; j++) {
                int totalBroken = prefixSum[i][j]
                        - prefixSum[i - damageSize][j]
                        - prefixSum[i][j - damageSize]
                        + prefixSum[i - damageSize][j - damageSize];
                if (totalBroken == damageSize * damageSize) {
                    return true; // Found a fully broken k x k square
                }
            }
        }
        return false;
    }

    private static int monitorBreakInstant(int gridLen, int gridWid, int damageSize, List<int[]> brokenPixels, int brokenPixelCount) {
        int[][] grid = new int[gridLen + 1][gridWid + 1];
        // Sort pixels by time
        brokenPixels.sort(Comparator.comparingInt(p -> p[2]));

        // Binary search for the minimum time
        int left = 0, right = brokenPixelCount - 1, result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Reset grid
            for (int i = 1; i <= gridLen; i++) Arrays.fill(grid[i], 0);

            // Activate pixels up to time mid
            for (int i = 0; i <= mid; i++) {
                int x = brokenPixels.get(i)[0];
                int y = brokenPixels.get(i)[1];
                grid[x][y] = 1;
            }

            // Check if the monitor is broken at this time
            if (isBroken(grid, gridLen, gridWid, damageSize)) {
                result = brokenPixels.get(mid)[2]; // Update result with the current time
                right = mid - 1; // Look for earlier times
            } else left = mid + 1; // Look for later times
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read inputs
        int gridLen = scanner.nextInt();
        int gridWid = scanner.nextInt();
        int damageSize = scanner.nextInt();
        int brokenPixelCount = scanner.nextInt();

        List<int[]> pixels = new ArrayList<>();

        for (int i = 0; i < brokenPixelCount; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int t = scanner.nextInt();
            pixels.add(new int[] {x, y, t});
        }

        // Output the result
        System.out.println(monitorBreakInstant(gridLen, gridWid, damageSize, pixels, brokenPixelCount));

        scanner.close();
    }
}
