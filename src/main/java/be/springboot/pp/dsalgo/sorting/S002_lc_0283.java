package be.springboot.pp.dsalgo.sorting;

public class S002_lc_0283 {
    public void moveZeroes(int[] nums) {
        int lastNonZero = 0; // Pointer for the next non-zero placement
        int n = nums.length;

        // Traverse the array
        for (int current = 0; current < n; current++) {
            if (nums[current] != 0) {
                // Swap non-zero element with the position of lastNonZero
                int temp = nums[lastNonZero];
                nums[lastNonZero] = nums[current];
                nums[current] = temp;
                lastNonZero++;
            }
        }
    }
}
