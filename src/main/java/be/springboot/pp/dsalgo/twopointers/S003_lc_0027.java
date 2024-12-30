package be.springboot.pp.dsalgo.twopointers;

public class S003_lc_0027 {
    public static int removeElement(int[] nums, int val) {
        // Pointer to track the position of valid elements
        int i = 0;

        // Traverse through the array
        for (int j = 0; j < nums.length; j++) {
            // If the current element is not equal to val, it is valid
            if (nums[j] != val) {
                nums[i] = nums[j]; // Move the valid element to the 'i' position
                i++; // Increment the pointer for valid elements
            }
        }

        // Return the count of valid elements
        return i;
    }
}
