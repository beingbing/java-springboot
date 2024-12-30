package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S002_gfg_count_pairs_2 {
    public static int findPairsWithSum(int[] arr, int N, int K) {
        // Step 1: Sort the array
        Arrays.sort(arr);

        // Step 2: Initialize pointers and pair count
        int left = 0, right = N - 1, count = 0;

        // Step 3: Use two-pointer technique to count pairs
        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == K) {
                // Count duplicates for arr[left]
                int countLeft = 1, countRight = 1;

                while (left < right && arr[left] == arr[left + 1]) {
                    countLeft++;
                    left++;
                }

                while (left < right && arr[right] == arr[right - 1]) {
                    countRight++;
                    right--;
                }

                // If left and right point to the same element
                if (arr[left] == arr[right]) count += (countLeft * (countLeft - 1)) / 2; // Combination formula
                else count += countLeft * countRight;

                left++;
                right--;
            } else if (sum < K) left++; // Increase sum by moving left pointer
            else right--; // Decrease sum by moving right pointer
        }

        return count;
    }
}
