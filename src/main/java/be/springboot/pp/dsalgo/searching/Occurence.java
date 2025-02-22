package be.springboot.pp.dsalgo.searching;

public class Occurence {
    public static int findFirst(int[] a, int key) {
        int n = a.length;
        int left = 0, right = n - 1;

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

    public static int findLast(int[] a, int key) {
        int n = a.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] > key) right = mid - 1;
            else if (a[mid] < key) left = mid + 1;
            else {
                if (mid == n - 1 || a[mid + 1] != key) return mid;
                else left = mid + 1;
            }
        }

        return -1;
    }

    public int[] searchRange(int[] a, int key) {
        int first = findFirst(a, key);
        if (first == -1) return new int[]{-1, -1};

        int last = findLast(a, key);
        return new int[]{first, last};
    }
}
