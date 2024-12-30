package be.springboot.pp.dsalgo.sorting;

import java.util.Scanner;

public class S004_lc_0031 {
    // Method to find the next permutation
    public static void findNextPermutation(int[] a) {
        int n = a.length;

        // Step 1: Find the pivot (rightmost element where a[i] < a[i+1])
        // right most break-point in descending streak
        int i = n - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;

        if (i >= 0) {
            // Step 2: Find the smallest number larger than a[i] to the right of a[ij
            // replace the break-point with the smallest strictly greater element from its right
            int j = n - 1;
            while (j >= 0 && a[j] <= a[i]) j--;
            // Swap a[i] and a[j]
            swap(a, i, j);
        }

        // Step 3: Reverse the subarray to the right of i (break-point)
        reverse(a, i + 1, n - 1);
    }

    // Helper method to swap two elements in the array
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Helper method to reverse a subarray
    private static void reverse(int[] a, int start, int end) {
        while (start < end) {
            swap(a, start, end);
            start++;
            end--;
        }
    }
}
