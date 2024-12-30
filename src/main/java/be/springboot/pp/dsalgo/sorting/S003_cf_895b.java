package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class S003_cf_895b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the size of the array, x, and k
        int n = sc.nextInt();
        long x = sc.nextLong();
        long k = sc.nextLong();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextLong();

        System.out.println(findRangeSum(a, n, x, k));
    }

    private static long findRangeSum(long[] a, int n, long x, long k) {
        // Sort the array to handle ordered pairs (i, j)
        Arrays.sort(a);

        long result = 0;

        //  For a fixed a[i], calculate the min and max value of R bound
        for (int i = 0; i < n; i++) { // by fixing i, we fix ai

            long l = ((a[i] + x - 1) / x) * x; // first integer divisible by x in [ai, aj]
            // Calculate the range of valid aj for given ai
            long minValue = l + (k - 1) * x; // min value of aj
            long maxValue = minValue + x - 1; // max value of aj (to include all numbers within the block ending at R).

            // Use binary search to find the count of valid j indices
            int lowerBound = lowerBound(a, minValue);
//            int strictUpperBound = upperBound(a, end);

//            result += strictUpperBound - lowerBound;

            int upperBound = upperBound(a, i, n, maxValue);

            if (lowerBound < n && a[lowerBound] <= maxValue)
                result += upperBound - lowerBound + 1;
        }

        return result;
    }

    // Binary search to find the first index where a[idx] >= value
    private static int lowerBound(long[] arr, long value) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= value) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    // Binary search to find the first index where a[idx] > value
    private static int upperBound(long[] arr, long value) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] > value) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    // Helper function: Binary search for the first element >= target
    private static int lowerBound(long[] a, int start, int end, long target) {
        int low = start, high = end - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // Helper function: Binary search for the last element <= target
    private static int upperBound(long[] a, int start, int end, long target) {
        int low = start, high = end - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
}
