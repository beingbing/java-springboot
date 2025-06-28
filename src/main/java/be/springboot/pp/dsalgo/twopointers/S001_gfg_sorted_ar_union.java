package be.springboot.pp.dsalgo.twopointers;

import java.util.ArrayList;

public class S001_gfg_sorted_ar_union {
    public static ArrayList<Integer> findUnion(int[] a, int[] b) {
        int n = a.length, m = b.length, i = 0, j = 0, val;
        ArrayList<Integer> ans = new ArrayList<>();

        while (i < n && j < m) {
            if (a[i] < b[j]) val = a[i++];
            else if (b[j] < a[i]) val = b[j++];
            else {
                val = a[i];
                i++;
                j++;
            }

            if (ans.isEmpty() || ans.getLast() != val) ans.add(val);
        }

        while (i < n) {
            if (ans.isEmpty() || ans.getLast() != a[i]) ans.add(a[i]);
            i++;
        }

        while (j < m) {
            if (ans.isEmpty() || ans.getLast() != b[j]) ans.add(b[j]);
            j++;
        }

        return ans;
    }
}
