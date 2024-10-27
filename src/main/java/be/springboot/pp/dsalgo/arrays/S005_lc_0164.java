package be.springboot.pp.dsalgo.arrays;

import java.util.Arrays;

public class S005_lc_0164 {
    public static int maxConsecutiveDifference(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // Step 1: Find the minimum and maximum values in the array.
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();

        // If all elements are the same, max consecutive difference is 0
        if (min == max) {
            return 0;
        }

        int n = nums.length;

        // Step 2: Calculate the minimum possible gap.
        int gap = (max - min) / (n - 1);
        if ((max - min) % (n - 1) != 0) gap++;
        int bucketSize = Math.max(1, gap); // Bucket size

        // Step 3: Create buckets to store min and max of each bucket.
        int[] bucketMin = new int[n];
        int[] bucketMax = new int[n];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // Step 4: Place elements into their corresponding buckets.
        for (int num : nums) {
            int bucketIndex = (num - min) / bucketSize;
            // step 5: track bucket Min/Max
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], num);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], num);
        }

        // Step 6: Find the maximum gap between consecutive non-empty buckets.
        int maxGap = 0;
        int prevMax = min; // The previous bucket's maximum value

        for (int i = 0; i < n; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE) continue; // Empty bucket, skip it
            // Update maxGap
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 9, 1};
        System.out.println("Maximum consecutive difference: " + maxConsecutiveDifference(arr)); // Output: 3
    }
}
