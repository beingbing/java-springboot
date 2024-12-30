package be.springboot.pp.dsalgo.sorting;

public class S003_lc_0493 {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    // Merge Sort function to count reverse pairs
    private int mergeSortAndCount(int[] nums, int left, int right) {
        int count = 0;

        if (left >= right) return count;

        int mid = left + (right - left) / 2;

        // Count reverse pairs in the left and right halves
        count += mergeSortAndCount(nums, left, mid);
        count += mergeSortAndCount(nums, mid + 1, right);

        // Count reverse pairs across the two halves
        count += countReversePairsAcross(nums, left, mid, right);

        // Merge the two sorted halves
        merge(nums, left, mid, right);

        return count;
    }

    // Count reverse pairs across two halves
    private int countReversePairsAcross(int[] nums, int left, int mid, int right) {
        int count = 0;
        int j = mid + 1;

        // For each element in the left half, count elements in the right half
        for (int i = left; i <= mid; i++) {
            while (j <= right && nums[i] > 2L * nums[j]) j++;
            count += (j - mid - 1);
        }

        return count;
    }

    // Merge two sorted halves
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // Merge the two halves
        while (i <= mid && j <= right)
            if (nums[i] <= nums[j]) temp[k++] = nums[i++];
            else temp[k++] = nums[j++];

        // Copy remaining elements from the left half
        while (i <= mid) temp[k++] = nums[i++];

        // Copy remaining elements from the right half
        while (j <= right) temp[k++] = nums[j++];

        // Copy merged array back to the original array
        for (int p = 0; p < temp.length; p++) nums[left + p] = temp[p];
    }
}
