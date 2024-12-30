package be.springboot.pp.dsalgo.twopointers;

import java.util.ArrayList;
import java.util.List;

public class S001_gfg_sorted_ar_union {
    public static List<Integer> findUnion(int[] a, int[] b) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        // Two-pointer approach to merge and find union
        while (i < a.length && j < b.length) {
            // Skip duplicates in a
            while (i > 0 && i < a.length && a[i] == a[i - 1]) i++;

            // Skip duplicates in b
            while (j > 0 && j < b.length && b[j] == b[j - 1]) j++;

            // If pointers are out of bounds after skipping
            if (i >= a.length || j >= b.length) break;

            if (a[i] < b[j]) {
                result.add(a[i]);
                i++;
            } else if (a[i] > b[j]) {
                result.add(b[j]);
                j++;
            } else {
                // Both elements are equal, add one of them
                result.add(a[i]);
                i++;
                j++;
            }
        }

        // Add remaining elements from a
        while (i < a.length) {
            if (i == 0 || a[i] != a[i - 1]) result.add(a[i]);
            i++;
        }

        // Add remaining elements from b
        while (j < b.length) {
            if (j == 0 || b[j] != b[j - 1]) result.add(b[j]);
            j++;
        }

        return result;
    }
}
