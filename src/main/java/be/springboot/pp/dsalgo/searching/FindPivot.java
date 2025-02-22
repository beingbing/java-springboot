package be.springboot.pp.dsalgo.searching;

public class FindPivot {
    public int search(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        int n = a.length;

        int pivotIdx = findPivot(a);
        if (pivotIdx == n - 1) return binarySearch(a, 0, n - 1, target);

        if (a[0] <= target && target <= a[pivotIdx]) return binarySearch(a, 0, pivotIdx, target);
        else return binarySearch(a, pivotIdx + 1, n - 1, target);
    }

    private int findPivot(int[] a) {
        int n = a.length;
        int low = 0, high = n - 1;

        if (a[low] <= a[high]) return n - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (mid < a.length - 1 && a[mid] > a[mid + 1]) return mid;

            if (a[low] <= a[mid]) low = mid + 1;
            else high = mid;
        }
        return low - 1;
    }

    private int binarySearch(int[] a, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) return mid;
            else if (a[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public int search2(int[] a, int target) {
        int n = a.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == target) return mid;

            if (a[left] <= a[mid]) {
                if (a[left] <= target && target < a[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                if (a[mid] < target && target <= a[right]) left = mid + 1;
                else right = mid - 1;
            }
        }

        return -1;
    }
}
