package be.springboot.pp.dsalgo.twopointers;

public class S002_lc_0011 {
    public static int maxArea(int[] height) {
        int left = 0; // Initialize left pointer
        int right = height.length - 1; // Initialize right pointer
        int maxArea = 0; // To store the maximum area

        // Loop until the pointers meet
        while (left < right) {
            // Calculate the area with current left and right pointers
            int width = right - left;
            int currentArea = width * Math.min(height[left], height[right]);

            // Update the maximum area if the current area is larger
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer pointing to the shorter line inward
            if (height[left] < height[right]) left++;
            else right--;
        }

        return maxArea; // Return the maximum area found
    }
}
