package be.springboot.pp.dsalgo.twopointers;

public class S001_lc_1248 {
    public static int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = 0;
        int oddCount = 0, result = 0;
        int prefixEven = 0; // Tracks even numbers before the first odd number in the window

        while (right < nums.length) {
            // If the current number is odd, increment the odd count
            if (isOdd(nums[right])) {
                oddCount++;
                prefixEven = 0; // Reset the prefix count for a new window
            }

            // If the window contains exactly k odd numbers
            // move left pointer ahead and count total left moves
            // each of them can be the starting index of subarray with k odds
            // having right pointer as an ending index
            while (oddCount == k) {
                // Count the number of subarrays ending at `right` that are valid
                prefixEven++;
                if (isOdd(nums[left])) oddCount--;
                left++;
            }

            // Add the prefix count to the result
            result += prefixEven;

            // Expand the window
            right++;
        }

        return result;
    }

    private static boolean isOdd(int n) {
        return n % 2 != 0;
    }
}
