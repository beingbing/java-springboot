package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S001_gfg_nge {
    public int[] nextGreaterElements(int[] arr, int n) {
        int[] nge = new int[n];
        Stack<Integer> stack = new Stack<>(); // Monotonic stack

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) stack.pop();
            nge[i] = stack.isEmpty() ? -1 : stack.peek(); // If stack is not empty, the top of the stack is the next greater element
            stack.push(arr[i]);
        }

        return nge;
    }
}

class NextGreaterElement {
    public int[] findNextLargerElements(int[] arr, int n) {
        int[] nge = new int[n];
        Stack<Integer> indexStack = new Stack<>(); // Stack to keep track of indices for elements which we need to find the next greater element

        for (int i = 0; i < n; i++) {
            while (!indexStack.isEmpty() && arr[i] > arr[indexStack.peek()]) nge[indexStack.pop()] = arr[i];
            indexStack.push(i);
        }

        while (!indexStack.isEmpty()) nge[indexStack.pop()] = -1;

        return nge;
    }
}
