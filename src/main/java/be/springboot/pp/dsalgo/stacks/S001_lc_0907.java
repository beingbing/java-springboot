package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S001_lc_0907 {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        int n = arr.length;

        int[] left = new int[n];
        int[] right = new int[n];

        // Monotonic stack for left counts
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // Pop elements greater than or equal to arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        // Clear stack for right counts
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements greater than arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        // Calculate the sum of contributions
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long contribution = (long) arr[i] * left[i] * right[i];
            sum = (sum + contribution) % MOD;
        }

        return (int) sum;
    }
}

class SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        final int MOD = 1_000_000_007;
        int n = arr.length;

        int[] nextSmallerIndex = new int[n];
        int[] previousSmallerOrEqualIndex = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop elements from the stack if they are greater than the current element
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) nextSmallerIndex[stack.pop()] = i; // Update NSE index for the popped element
            stack.push(i); // Push the current index onto the stack
        }
        while (!stack.isEmpty()) nextSmallerIndex[stack.pop()] = n; // For remaining elements in the stack, NSE is 'n' (out of bounds)

        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack if they are strictly greater than the current element
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) previousSmallerOrEqualIndex[stack.pop()] = i; // Update PSE index for the popped element
            stack.push(i); // Push the current index onto the stack
        }
        while (!stack.isEmpty()) previousSmallerOrEqualIndex[stack.pop()] = -1; // For remaining elements in the stack, PSE is '-1' (out of bounds)

        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            long leftCount = i - previousSmallerOrEqualIndex[i]; // Distance to the previous smaller or equal element
            long rightCount = nextSmallerIndex[i] - i; // Distance to the next smaller element
            long contribution = (leftCount * rightCount) % MOD * arr[i] % MOD;
            totalSum = (totalSum + contribution) % MOD;
        }

        return (int) totalSum;
    }
}
