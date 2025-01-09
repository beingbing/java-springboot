package be.springboot.pp.dsalgo.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class S003_lc_0480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        // Define two TreeSets for the left and right partitions
        TreeSet<Integer> leftSet = new TreeSet<>(createReversedComparator(nums));
//        TreeSet<Integer> leftSet = new TreeSet<>(createComparator(nums).reversed()); // can also do this
        TreeSet<Integer> rightSet = new TreeSet<>(createComparator(nums));

        int n = nums.length;
        double[] result = new double[n - k + 1];

        // Initialize the first window
        for (int i = 0; i < k; i++) leftSet.add(i); // Add initial indices to left set
        balanceSets(leftSet, rightSet);
        result[0] = getMedian(leftSet, rightSet, nums, k);

        // Process remaining windows
        for (int i = k; i < n; i++) {
            int indexToRemove = i - k; // Index sliding out of the window
            if (!leftSet.remove(indexToRemove)) rightSet.remove(indexToRemove); // Remove from right if not in left

            rightSet.add(i); // Add new index to the right set
            leftSet.add(rightSet.pollFirst()); // Balance: Move smallest from right to left
            balanceSets(leftSet, rightSet); // Ensure sets are balanced

            // Compute and store the median
            result[i - k + 1] = getMedian(leftSet, rightSet, nums, k);
        }

        return result;
    }

    private Comparator<Integer> createComparator(int[] nums) {
        return (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
    }

    private Comparator<Integer> createReversedComparator(int[] nums) {
        return (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[b], nums[a]) : Integer.compare(b, a);
    }

    private void balanceSets(TreeSet<Integer> leftSet, TreeSet<Integer> rightSet) {
        while (leftSet.size() > rightSet.size()) rightSet.add(leftSet.pollFirst());
        while (rightSet.size() > leftSet.size() + 1) leftSet.add(rightSet.pollFirst());
    }

    private double getMedian(TreeSet<Integer> leftSet, TreeSet<Integer> rightSet, int[] nums, int k) {
        if (k % 2 == 0) return ((double) nums[leftSet.first()] + nums[rightSet.first()]) / 2;
        else return (double) nums[rightSet.first()];
    }

    public static void main(String[] args) {
        S003_lc_0480 slidingWindowMedian = new S003_lc_0480();

        // Test Case 1
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Test Case 1 Output: " + slidingWindowMedian.medianSlidingWindow(nums1, k1));

        // Test Case 2
        int[] nums2 = {1, 2, 3, 4, 2, 3, 1, 4, 2};
        int k2 = 3;
        System.out.println("Test Case 2 Output: " + Arrays.toString(slidingWindowMedian.medianSlidingWindow(nums2, k2)));
    }
}
