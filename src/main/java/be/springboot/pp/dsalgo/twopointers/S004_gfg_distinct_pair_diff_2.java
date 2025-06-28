package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S004_gfg_distinct_pair_diff_2 {
    public static int countPairsWithDifference(int[] arr, int K) {
        Arrays.sort(arr); // Step 1: Sort the array
        int count = 0;
        int i = 0, j = 1;

        while (j < arr.length) {
            int diff = arr[j] - arr[i];

            if (diff == K) {
                if (K == 0) {
                    // For K == 0, count distinct duplicates
                    count++;
                    // Advance both pointers to skip duplicates
                    while (j < arr.length && arr[j] == arr[j - 1]) j++;
                    while (i < j && arr[i] == arr[i + 1]) i++;
                } else {
                    count++; // Valid pair found
                    i++;
                    j++;
                    // Skip duplicates for distinct pairs
                    while (j < arr.length && arr[j] == arr[j - 1]) j++;
                    while (i < j && arr[i] == arr[i - 1]) i++;
                }
            } else if (diff < K) {
                j++; // Increase the difference
            } else {
                i++; // Decrease the difference
                if (i == j) j++; // Ensure j is always ahead of i
            }
        }

        return count;
    }

    public int findDistinctDiffPairs(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length, count = 0, left = 0, right = 1;

        while (right < n) {
            if (left == right) right++;
            int diff = a[right] - a[left];

            if (diff == k) {
                count++;
                int curLeft = left;
                int curRight = right;
                while (left < n && a[left] == curLeft) left++;
                while (right < n && a[right] == curRight) right++;
            } else if (diff < k) right++;
            else left++;
        }
        return count;
    }
}