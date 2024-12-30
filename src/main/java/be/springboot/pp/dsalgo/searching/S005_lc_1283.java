package be.springboot.pp.dsalgo.searching;

import java.util.Scanner;

public class S005_lc_1283 {

    // Helper function to compute the sum of elements divided by `divisor`
    private static boolean canAchieveThreshold(int[] nums, int divisor, int threshold) {
        int sum = 0;
        for (int num : nums) {
            // Division with rounding up using (num + divisor - 1) / divisor
            sum += (num + divisor - 1) / divisor;
            if (sum > threshold) return false;
        }
        return sum <= threshold;
    }

    // Helper function to compute the maximum value in the array
    private static int getMax(int[] nums) {
        int max = nums[0];
        for (int num : nums) if (num > max) max = num;
        return max;
    }

    // Function to find the smallest divisor such that the division sum is <= threshold
    public static int smallestDivisor(int[] nums, int threshold) {
        int left = 1;  // Minimum possible divisor
        int right = getMax(nums); // Maximum divisor as the largest number in nums
        int result = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canAchieveThreshold(nums, mid, threshold)) {
                result = mid;
                // If the sum with divisor `mid` is within threshold, try smaller divisors
                right = mid - 1;
            } else {
                // If the sum exceeds threshold, increase the divisor
                left = mid + 1;
            }
        }

        // `left` is now the smallest divisor that works
        return result;
    }
}
