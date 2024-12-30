package be.springboot.pp.dsalgo.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S002_lc_0349_1 {
    public static List<Integer> intersection(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                // Add to result if it's not a duplicate
                if (result.isEmpty() || result.getLast() != a[i]) result.add(a[i]);
                i++;
                j++;
            } else if (a[i] < b[j]) i++; // Move pointer in a
            else j++; // Move pointer in b
        }

        return result;
    }
}
