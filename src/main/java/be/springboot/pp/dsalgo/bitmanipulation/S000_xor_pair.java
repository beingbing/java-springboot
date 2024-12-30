package be.springboot.pp.dsalgo.bitmanipulation;

import java.util.HashSet;

public class S000_xor_pair {
    public static boolean doesPairExist(int[] arr, int k) {
        // Use a HashSet to store seen elements
        HashSet<Integer> seen = new HashSet<>();

        // Traverse the array
        for (int num : arr) {
            // Compute the required counterpart
            int required = num ^ k;

            // Check if the counterpart exists in the set
            if (seen.contains(required)) return true; // Found the pair

            // Add the current number to the set
            seen.add(num);
        }

        // If no such pair is found, return false
        return false;
    }
}
