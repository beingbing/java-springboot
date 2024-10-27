package be.springboot.pp.dsalgo.arrays;

public class S006_lc_0041 {

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Place each number in its correct index if possible
        for (int i = 0; i < n; i++)
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
                swap(nums, nums[i] - 1, i); // Swap arr[i] with arr[arr[i] - 1]

        // Step 2: Identify the first position where arr[i] != i + 1
        for (int i = 0; i < n; i++) if (nums[i] != i + 1) return i + 1;  // This is the smallest missing positive integer

        // Step 3: If all numbers from 1 to n are present, return n + 1
        return n + 1;
    }
}
