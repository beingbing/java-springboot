package be.springboot.pp.dsalgo.searching;

public class PeakElement {
    public int findPeakElement(int[] a) {
        int n = a.length;

        // Edge cases
        if (n == 1) return 0;
        if (a[0] > a[1]) return 0;
        if (a[n - 2] < a[n - 1]) return n - 1;

        int left = 1, right = n - 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid - 1] < a[mid] && a[mid] > a[mid + 1]) return mid;
            else if (a[mid] < a[mid - 1]) right = mid - 1;
            else left = mid + 1;
        }

        return -1; // dummy value
    }
}
