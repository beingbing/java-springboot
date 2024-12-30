package be.springboot.pp.dsalgo.searching;

import java.util.Scanner;

public class S003_lc_1011 {
    // Function to find the minimum ship capacity needed to ship within 'days'
    private static int findMinCapacity(int[] weights, int days) {
        int left = 0, right = 0;

        // Determine bounds for binary search
        for (int weight : weights) {
            left = Math.max(left, weight); // Minimum capacity must be at least max weight
            right += weight;               // Max capacity is sum of all weights (one trip)
        }

        // Binary search for the smallest valid capacity
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Check if 'mid' capacity can ship packages within the given days
            if (canShipInDays(weights, days, mid)) right = mid; // Try a smaller capacity
            else left = mid + 1; // Increase capacity if not feasible
        }

        return left; // Minimum-feasible capacity
    }

    // Function to check if a given capacity can ship all packages within 'days'
    private static boolean canShipInDays(int[] weights, int days, int capacity) {
        int daysNeeded = 1; // Start with the first day
        int currentLoad = 0; // Current load on the ship for the day

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                daysNeeded++; // Exceeds capacity, start a new day
                currentLoad = 0;
            }
            currentLoad += weight; // Add the package to the current day's load

            // If days exceed allowed, capacity is insufficient
            if (daysNeeded > days) return false;
        }

        return true; // Shipping within allowed days is feasible
    }
}
