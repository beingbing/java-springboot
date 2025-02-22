package be.springboot.pp.dsalgo.searching;

public class MaxSubarrayLength {
    public int findMaxK(int[] a, int x) {
        int left = 1, right = a.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canHaveSizeK(a, mid, x)) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }

    private boolean canHaveSizeK(int[] a, int k, int x) {
        int n = a.length;

        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += a[i];

        if (windowSum > x) return false;

        for (int i = k; i < n; i++) {
            windowSum += a[i] - a[i - k];
            if (windowSum > x) return false;
        }

        return true;
    }
}
