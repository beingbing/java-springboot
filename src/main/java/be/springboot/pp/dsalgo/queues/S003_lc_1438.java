package be.springboot.pp.dsalgo.queues;

import java.util.Deque;
import java.util.LinkedList;

public class S003_lc_1438 {
    public static int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new LinkedList<>(); // To maintain max elements
        Deque<Integer> minDeque = new LinkedList<>(); // To maintain min elements
        int left = 0, maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            // Update maxDeque
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] < nums[right]) maxDeque.pollLast();
            maxDeque.addLast(right);

            // Update minDeque
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] > nums[right]) minDeque.pollLast();
            minDeque.addLast(right);

            // Check if the window is valid
            while (!maxDeque.isEmpty() && !minDeque.isEmpty()
                    && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                // Shrink window from the left
                if (maxDeque.peekFirst() == left) maxDeque.pollFirst();
                if (minDeque.peekFirst() == left) minDeque.pollFirst();
                left++;
            }

            // Update the maximum length of the valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{8, 2, 4, 7}, 4)); // Output: 2
        System.out.println(longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5)); // Output: 4
        System.out.println(longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0)); // Output: 3
    }
}
