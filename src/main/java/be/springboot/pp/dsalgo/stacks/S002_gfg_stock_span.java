package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S002_gfg_stock_span {
    public int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n]; // Array to store spans
        Stack<Integer> stack = new Stack<>(); // Stack to store indices of prices

        for (int i = 0; i < n; i++) {
            // Pop elements from the stack while the current price is greater or equal
            while (!stack.isEmpty() && prices[i] >= prices[stack.peek()]) stack.pop();

            if (stack.isEmpty()) span[i] = i + 1; // All previous days are less than or equal
            else span[i] = i - stack.peek(); // Span is the difference in indices

            stack.push(i);
        }

        return span;
    }
}
