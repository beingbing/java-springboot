package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S004_self_count_pair_diff_2 {
    public static int countPairsWithDifference(int[] arr, int K) {
        Arrays.sort(arr); // Step 1: Sort the array

        int count = 0;
        int i = 0, j = 1;

        while (i < arr.length && j < arr.length) {
            int diff = arr[j] - arr[i];

            if (diff == K) {
                if (K == 0) {
                    // Count pairs for K = 0
                    int freq = 0, duplicate = arr[i];
                    while (i < arr.length && arr[i] == duplicate) {
                        freq++;
                        i++;
                    }
                    count += (freq * (freq - 1)) / 2; // Number of ways to pick 2 from freq
                    j = i + 1; // Move to the next distinct number
                } else {
                    // Count duplicates around i and j
                    int countI = 1, countJ = 1;

                    while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                        countI++;
                        i++;
                    }

                    while (j + 1 < arr.length && arr[j] == arr[j + 1]) {
                        countJ++;
                        j++;
                    }

                    // Total pairs contributed by duplicates
                    count += countI * countJ;

                    // Move pointers
                    i++;
                    j++;
                }
            } else if (diff < K) {
                j++; // Increase the difference
            } else {
                i++; // Decrease the difference
                if (i == j) j++; // Ensure j stays ahead of i
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        int[] arr1 = {5, 20, 3, 2, 5, 80};
        int K1 = 78;
        System.out.println(countPairsWithDifference(arr1, K1)); // Output: 1

        int[] arr2 = {1, 1, 1, 1};
        int K2 = 0;
        System.out.println(countPairsWithDifference(arr2, K2)); // Output: 6

        int[] arr3 = {1, 5, 3, 4, 2};
        int K3 = 2;
        System.out.println(countPairsWithDifference(arr3, K3)); // Output: 3

        int[] arr4 = {1, 2, 2, 2, 3};
        int K4 = 1;
        System.out.println(countPairsWithDifference(arr4, K4)); // Output: 6

        int[] arr5 = {1, 2, 2, 2, 3, 5, 5, 5};
        int K5 = 3;
        System.out.println(countPairsWithDifference(arr5, K5)); // Output: 9
    }
}
