package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S001_lc_0016 {
    public static int threeSumClosest(int[] nums, int target) {
        // Sort the array to facilitate two-pointer traversal
        Arrays.sort(nums);
        int closestSum = Integer.MAX_VALUE;
        int result = 0;

        // Iterate through the array, fixing one element at a time
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1; // Pointer to the next element
            int right = nums.length - 1; // Pointer to the last element

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // Update closestSum if currentSum is closer to the target
                if (Math.abs(target - currentSum) < Math.abs(target - closestSum)) {
                    closestSum = currentSum;
                    result = currentSum;
                }

                // Move pointers based on comparison with target
                if (currentSum < target) left++; // Need a larger sum
                else if (currentSum > target) right--; // Need a smaller sum
                else return currentSum; // exact match found
            }
        }

        return result;
    }
}
