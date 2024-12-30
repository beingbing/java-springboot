package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S003_gfg_pair_diff_2 {
    public static String hasPairWithDifference(int[] arr, int N, int K) {
        // Step 1: Sort the array in ascending order
        Arrays.sort(arr);

        // Step 2: Initialize two pointers
        int i = 0, j = 1;

        // Step 3: Traverse the array using two-pointer technique
        while (j < N) {
            // Ensure pointers are not overlapping
            if (i == j) {
                j++;
                continue;
            }

            int diff = arr[j] - arr[i];

            // Check if the difference equals K
            if (diff == K) return "Yes"; // Pair found
            else if (diff < K) j++; // Increase difference by moving right pointer
            else i++; // Decrease difference by moving left pointer
        }

        // Step 4: No pair found
        return "No";
    }
}
