package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S001_gfg_segregate_ev_od {
    public void segregateEvenOdd(int[] a) {
        int n = a.length, slow = 0, fast = 0;

        while (fast < n) {
            if (a[fast] % 2 == 0) {
                swap(a, slow, fast);
                slow++;
            }
            fast++;
        }

        Arrays.sort(a, 0, slow);
        Arrays.sort(a, slow, n);
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
