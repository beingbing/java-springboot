package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class S001_cc_maxdiff {
    // Function to calculate maximum weight difference
    public static int maxWeightDifference(int[] weights, int N, int K) {
        Arrays.sort(weights); // Step 1: Sort the array

        int totalWeight = 0;
        for (int weight : weights) totalWeight += weight; // Calculate total weight of all items

        // Step 2: Compute weight of the K lightest items
        int lightestK = 0;
        for (int i = 0; i < K; i++) lightestK += weights[i];

        // Step 3: Compute weight of the K heaviest items
        int heaviestK = 0;
        for (int i = N - K; i < N; i++) heaviestK += weights[i];

        // Step 4: Calculate the differences for both scenarios
        int case1 = (totalWeight - lightestK) - lightestK;
        int case2 = heaviestK - (totalWeight - heaviestK);

        // Return the maximum difference
        return Math.max(case1, case2);
    }
}
