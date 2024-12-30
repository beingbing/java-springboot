package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S001_gfg_pair_sum_2 {
    public static boolean hasPairWithSum(int[] arr, int N, int K) {
        // Sort the array
        Arrays.sort(arr);

        // Use two pointers to find the pair
        int low = 0, high = N - 1;

        while (low < high) {
            int sum = arr[low] + arr[high];

            if (sum == K) return true; // Pair found
            else if (sum < K) low++; // Move the lower pointer to the right
            else high--; // Move the higher pointer to the left
        }

        return false; // No pair found
    }
}
