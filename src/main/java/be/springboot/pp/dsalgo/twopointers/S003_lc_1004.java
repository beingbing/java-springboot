package be.springboot.pp.dsalgo.twopointers;

public class S003_lc_1004 {
    public static int longestOnes(int[] nums, int k) {
        int left = 0; // Start of the sliding window
        int maxLength = 0; // Maximum length of valid window
        int zeroCount = 0; // Count of 0's in the current window

        // Iterate through the array with the right pointer
        for (int right = 0; right < nums.length; right++) {
            // If the current element is 0, increase the zero count
            if (nums[right] == 0) zeroCount++;

            // If zero count exceeds k, shrink the window from the left
            while (zeroCount > k) {
                if (nums[left] == 0) zeroCount--; // Decrease zero count as we move past a 0
                left++; // Shrink the window
            }

            // Update the maximum length of the window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
