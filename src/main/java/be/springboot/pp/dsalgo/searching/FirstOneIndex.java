package be.springboot.pp.dsalgo.searching;

public class FirstOneIndex {
    private int findFirstOneIndex(int[] a) {
        int n = a.length;
        int left = 0, right = n - 1;
        int key = 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] > key) right = mid - 1;
            else if (a[mid] < key) left = mid + 1;
            else {
                if (mid == 0 || a[mid - 1] != key) return mid;
                else right = mid - 1;
            }
        }

        return -1;
    }
}
