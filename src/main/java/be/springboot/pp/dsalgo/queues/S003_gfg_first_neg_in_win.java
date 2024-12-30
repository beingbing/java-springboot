package be.springboot.pp.dsalgo.queues;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class S003_gfg_first_neg_in_win {
    public static List<Integer> firstNegativeInWindow(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>(); // Stores indices of negative numbers

        for (int i = 0; i < arr.length; i++) {
            // Add current element index to deque if it's negative
            if (arr[i] < 0) deque.offerLast(i);

            // Remove elements that are out of the current window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) deque.pollFirst();

            // For fully formed windows, add the result
            if (i >= k - 1) {
                if (!deque.isEmpty()) result.add(arr[deque.peekFirst()]); // First negative in window
                else result.add(0); // No negative number in window
            }
        }

        return result;
    }
}
