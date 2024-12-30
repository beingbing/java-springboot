package be.springboot.pp.dsalgo.sorting;

public class S002_lc_0977 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];  // Result array to store sorted squares
        int left = 0;  // Pointer to start of nums
        int right = n - 1;  // Pointer to end of nums
        int index = n - 1;  // Fill the result array from the end

        // Compare absolute values from both ends and fill result array
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;  // Move left pointer
            } else {
                result[index] = rightSquare;
                right--;  // Move right pointer
            }
            index--;  // Move to the next position in result
        }

        return result;
    }
}
