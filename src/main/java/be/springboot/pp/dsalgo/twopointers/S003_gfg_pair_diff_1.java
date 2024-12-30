package be.springboot.pp.dsalgo.twopointers;

import java.util.HashSet;

public class S003_gfg_pair_diff_1 {
    public static String hasPairWithDifference(int[] arr, int N, int K) {
        // Step 1: Initialize a HashSet to store visited elements
        HashSet<Integer> seen = new HashSet<>();

        // Step 2: Traverse the array
        for (int num : arr) {
            // Step 3: Check if the complement numbers exist in the set
            if (seen.contains(num + K) || seen.contains(num - K)) return "Yes";
            // Step 4: Add the current number to the set
            seen.add(num);
        }

        // Step 5: If no pair is found, return "No"
        return "No";
    }
}
