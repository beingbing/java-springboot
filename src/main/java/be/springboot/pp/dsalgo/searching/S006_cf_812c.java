package be.springboot.pp.dsalgo.searching;

import java.util.Arrays;

public class S006_cf_812c {
    // Function to calculate the minimum total cost for buying `k` items
    private static long calculateCost(int[] baseCosts, int n, int k) {
        long[] adjustedCosts = new long[n];
        for (int i = 0; i < n; i++)
            adjustedCosts[i] = (long) baseCosts[i] + (long) (i + 1) * k;

        // Sort adjusted costs to find the minimum total cost for `k` items
        Arrays.sort(adjustedCosts);

        long totalCost = 0;
        for (int i = 0; i < k; i++) totalCost += adjustedCosts[i];

        return totalCost;
    }

    // Main function to perform binary search and determine the max souvenirs that can be bought
    public static int[] maxSouvenirs(int[] baseCosts, int n, long budget) {
        int left = 0, right = n, maxItems = 0;
        long minCost = 0;

        // Binary search on the number of items `k`
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long cost = calculateCost(baseCosts, n, mid);

            if (cost <= budget) {
                // If we can afford `mid` items, update maxItems and minCost
                maxItems = mid;
                minCost = cost;
                left = mid + 1;
            } else right = mid - 1; // Reduce the number of items if over budget
        }

        return new int[]{maxItems, (int) minCost};
    }
}
