package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S001_lc_0976 {
    private void reverse(int[] a) {
        int left = 0, right = a.length - 1;
        while (left < right) {
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }
    }

    public int largestPerimeter(int[] a) {
        Arrays.sort(a);
        reverse(a);
        for (int i = 0; i < a.length - 2; i++)
            if (a[i] < a[i + 1] + a[i + 2])
                return a[i] + a[i + 1] + a[i + 2];

        return 0;
    }
}
