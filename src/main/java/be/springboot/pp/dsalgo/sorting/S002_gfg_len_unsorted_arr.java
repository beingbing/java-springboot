package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S002_gfg_len_unsorted_arr {
    public static int[] findUnsortedSubarray(int[] a, int n) {
        int max = a[0], min = a[n - 1], end = -1, start = -1;

        // Find the end index of the unsorted subarray
        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
            if (a[i] < max) end = i;
        }

        // Find the start index of the unsorted subarray
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, a[i]);
            if (a[i] > min) start = i;
        }

        // If the array is already sorted
        if (start == -1 && end == -1) return new int[]{0, 0};

        return new int[]{start, end};
    }
}
