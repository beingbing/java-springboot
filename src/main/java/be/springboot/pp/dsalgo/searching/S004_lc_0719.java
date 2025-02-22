package be.springboot.pp.dsalgo.searching;

import java.util.Arrays;

public class S004_lc_0719 {
    public int smallestDistancePair(int[] a, int k) {
        Arrays.sort(a);

        int n = a.length;
        int low = 0, high = a[n - 1] - a[0];

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (countPairs(a, mid) >= k) high = mid - 1;
            else low = mid + 1;
        }

        return low;
    }

    private int countPairs(int[] a, int target) {
        int n = a.length, count = 0, left = 0;

        for (int right = 0; right < n; right++) {
            while (a[right] - a[left] > target) left++;
            count += right - left;
        }

        return count;
    }
}
