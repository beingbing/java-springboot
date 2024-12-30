package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class S002_gfg_manhattan_dist {
    public static long calculateDistanceSum(int[] coordinates, int n) {
        Arrays.sort(coordinates); // Step 1: Sort the coordinates

        long prefixSum = 0; // Tracks prefix sum till (i - 1)
        long totalSum = 0;  // Tracks total sum of all contributions

        for (int i = 0; i < n; i++) {
            long currentContribution = (long) coordinates[i] * i - prefixSum;
            totalSum = totalSum + currentContribution;

            prefixSum = prefixSum + coordinates[i];
        }

        return totalSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // Number of points
        int[] xCoords = new int[n];
        int[] yCoords = new int[n];

        for (int i = 0; i < n; i++) {
            xCoords[i] = sc.nextInt();
            yCoords[i] = sc.nextInt();
        }

        // Step 2: Compute contributions for x- and y-coordinates separately
        long xSum = calculateDistanceSum(xCoords, n);
        long ySum = calculateDistanceSum(yCoords, n);

        // Step 3: Combine results and print
        System.out.println(xSum + ySum);
        sc.close();
    }
}
