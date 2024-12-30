package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class S001_lc_0870 {
    public static int[] maximizeAdvantage(int[] a1, int[] a2) {
        int n = a1.length;

        Arrays.sort(a1);

        int[][] a2WithIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            a2WithIndex[i][0] = a2[i]; // value
            a2WithIndex[i][1] = i;     // index
        }
        Arrays.sort(a2WithIndex, Comparator.comparingInt(a -> a[0]));

        int[] result = new int[n];

        // Two pointers for a1
        int low = 0, high = n - 1;

        for (int i = 0; i < n; i++) {
            // Get the current value of a2 (sorted)
            int value = a2WithIndex[i][0];
            int index = a2WithIndex[i][1];

            // Assign the smallest number in a1 that is greater than a2[i]
            if (a1[low] > value) {
                result[index] = a1[low];
                low++;
            } else {
                // Assign the largest number in a1 (wasted value)
                result[index] = a1[high];
                high--;
            }
        }

        return result;
    }
}
