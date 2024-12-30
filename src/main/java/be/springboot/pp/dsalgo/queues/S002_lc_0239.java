package be.springboot.pp.dsalgo.queues;

import java.util.Deque;
import java.util.LinkedList;

public class S002_lc_0239 {

    // Inserts the current index into the deque while maintaining a decreasing order of values corresponding to indices stored in the deque.
    private void insertAtBack(Deque<Integer> deque, int currentIndex, int[] nums) {
        // Remove indices from the back whose corresponding values are smaller than nums[currentIndex].
        while (!deque.isEmpty() && nums[currentIndex] >= nums[deque.peekLast()]) deque.pollLast(); // Pop smaller elements as they are useless for the max window.
        deque.offerLast(currentIndex); // Add the current index at the back of the deque.
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0]; // Edge case: If input array is empty or window size is invalid.

        int n = nums.length; // Length of the input array
        int[] maxValuesInWindows = new int[n - k + 1]; // Result array to store maximums
        Deque<Integer> deque = new LinkedList<>(); // Deque to store indices of array elements

        for (int i = 0; i < k; i++) insertAtBack(deque, i, nums); // Process the first k elements (to initialize the deque for the first window)

        for (int i = k; i < n; i++) { // Iterate over the remaining elements to process subsequent windows
            maxValuesInWindows[i - k] = nums[deque.peekFirst()]; // The front of the deque always contains the index of the maximum value

            // Remove the index at the front if it's out of the current window range
            if (deque.peekFirst() == i - k) deque.pollFirst(); // Remove the front element as it slides out of the window

            insertAtBack(deque, i, nums); // Insert the current index into the deque
        }

        maxValuesInWindows[n - k] = nums[deque.peekFirst()];

        return maxValuesInWindows;
    }
}
