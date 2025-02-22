package be.springboot.pp.dsalgo.searching;

public class BinarySearch {

    public int searchInsertPosition(int[] a, int key) {
        int n = a.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] > key) right = mid - 1;
            else if (a[mid] < key) left = mid + 1;
            else return mid;
        }

        return left;
    }
}
