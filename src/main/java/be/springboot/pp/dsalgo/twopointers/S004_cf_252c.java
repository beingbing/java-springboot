package be.springboot.pp.dsalgo.twopointers;

import java.util.Scanner;

public class S004_cf_252c {
    private static long findPointsCount(int[] x, int d, int n) {
        long result = 0; // Use long to avoid overflow for large n
        int k = 0;

        for (int i = 0; i < n; i++) {
            // Expand the window: move 'k' to the right while the condition is satisfied
            while (k < n && x[k] - x[i] <= d) k++;

            // Calculate the number of triplets in the range [i, k-1]
            int len = k - i - 1; // -1 because i is fixed, so (actual_length - 1)
            // we can choose any two points out of given length, as third is fixed at i
            if (len >= 2) result += (long)  (len - 1) * len / 2;
        }

        return result;
    }
}
