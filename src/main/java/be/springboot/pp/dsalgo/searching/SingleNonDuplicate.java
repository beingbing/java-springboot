package be.springboot.pp.dsalgo.searching;

public class SingleNonDuplicate {
    public int singleNonDuplicate(int[] a) {
        int n = a.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid == n - 1) return a[mid];
            if (mid % 2 == 1) mid--;

            if (a[mid] == a[mid+1]) left = mid + 2;
            else right = mid - 1;
        }

        return a[left];
    }

}
