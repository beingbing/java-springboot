package be.springboot.pp.dsalgo.searching;

public class S005_lc_1283 {
    private boolean canAchieveThreshold(int[] a, int divisor, int threshold) {
        int sum = 0;
        for (int num : a) {
            sum += (num + divisor - 1) / divisor; // division rounding up
            if (sum > threshold) return false;
        }
        return sum <= threshold;
    }

    private int getMax(int[] a) {
        int max = a[0];
        for (int num : a) if (num > max) max = num;
        return max;
    }

    public int smallestDivisor(int[] a, int threshold) {
        int left = 1;  // Minimum possible divisor
        int right = getMax(a); // Maximum divisor as the largest number in a

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canAchieveThreshold(a, mid, threshold)) right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }
}
