package be.springboot.pp.dsalgo.sorting;

public class S003_lc_0327 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        // Step 1: Compute prefix sums
        long[] prefixSums = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        // Step 2: Use modified merge sort to count range sums
        return countAndSort(prefixSums, 0, prefixSums.length - 1, lower, upper);
    }

    private int countAndSort(long[] prefixSums, int left, int right, int lower, int upper) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;

        // Recursively count in left and right halves
        int count = countAndSort(prefixSums, left, mid, lower, upper)
                + countAndSort(prefixSums, mid + 1, right, lower, upper);

        // Count cross-pairs
        int l = mid + 1, r = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (l <= right && prefixSums[l] - prefixSums[i] < lower) l++;
            while (r <= right && prefixSums[r] - prefixSums[i] <= upper) r++;
            count += (r - l);
        }

        // Merge step
        long[] temp = new long[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (prefixSums[i] <= prefixSums[j]) {
                temp[k++] = prefixSums[i++];
            } else {
                temp[k++] = prefixSums[j++];
            }
        }

        while (i <= mid) temp[k++] = prefixSums[i++];
        while (j <= right) temp[k++] = prefixSums[j++];

        System.arraycopy(temp, 0, prefixSums, left, temp.length);

        return count;
    }

    public static void main(String[] args) {
        S003_lc_0327 solution = new S003_lc_0327();
        int[] nums = {-2, 5, -1};
        int lower = -2, upper = 2;

        System.out.println(solution.countRangeSum(nums, lower, upper)); // Output: 3
    }
}
