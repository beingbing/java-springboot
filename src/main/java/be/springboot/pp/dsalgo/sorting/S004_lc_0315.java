package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayList;
import java.util.List;

public class S004_lc_0315 {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        int[] indices = new int[n]; // To track original indices
        for (int i = 0; i < n; i++) indices[i] = i;

        mergeSort(nums, indices, counts, 0, n - 1);
        List<Integer> result = new ArrayList<>();
        for (int count : counts) result.add(count);
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, int[] counts, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, counts, left, mid);
        mergeSort(nums, indices, counts, mid + 1, right);
        merge(nums, indices, counts, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, int[] counts, int left, int mid, int right) {
        int[] tempIndices = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        int rightCount = 0; // Number of elements smaller in the right half

        while (i <= mid && j <= right) {
            if (nums[indices[j]] < nums[indices[i]]) {
                tempIndices[k++] = indices[j++];
                rightCount++;
            } else {
                counts[indices[i]] += rightCount;
                tempIndices[k++] = indices[i++];
            }
        }

        while (i <= mid) {
            counts[indices[i]] += rightCount;
            tempIndices[k++] = indices[i++];
        }

        while (j <= right) tempIndices[k++] = indices[j++];

        // Copy back to original indices array
        for (int p = 0; p < tempIndices.length; p++) indices[left + p] = tempIndices[p];
    }

    public static void main(String[] args) {
        S004_lc_0315 solution = new S004_lc_0315();
        int[] nums = {5, 2, 6, 1};
        System.out.println("Counts of smaller numbers: " + solution.countSmaller(nums)); // Output: [2, 1, 1, 0]
    }
}
