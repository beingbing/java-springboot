package be.springboot.pp.dsalgo.queues;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class S002_lc_0862 {
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1]; // Prefix sum array
        Arrays.fill(prefix, 0);
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];

        Deque<Integer> deque = new LinkedList<>();
        int minLength = n+1;

        for (int j = 0; j <= n; j++) {
            // Check and update the minimum length if the condition is satisfied
            while (!deque.isEmpty() && prefix[j] - prefix[deque.peekFirst()] >= k)
                minLength = Math.min(minLength, j - deque.pollFirst());

            // Maintain the deque as a monotonic queue
            while (!deque.isEmpty() && prefix[j] <= prefix[deque.peekLast()]) deque.pollLast();

            deque.addLast(j); // Add the current index to the deque
        }

        return minLength == n+1 ? -1 : minLength;
    }
}
