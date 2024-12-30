package be.springboot.pp.dsalgo.stacks;

import java.util.Arrays;
import java.util.Stack;

public class S001_lc_0503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n]; // Result array to store the next greater elements
        Stack<Integer> stack = new Stack<>(); // Stack to store indices
        Arrays.fill(res, -1); // Initialize result array with -1

        for (int i = 0; i < 2 * n; i++) { // Iterate twice to simulate circular behavior
            int currentIndex = i % n; // Circular index
            // Resolve elements in the stack
            while (!stack.isEmpty() && nums[currentIndex] > nums[stack.peek()]) {
                int index = stack.pop();
                res[index] = nums[currentIndex]; // Update result
            }
            // Only push indices from the first iteration
            if (i < n) {
                stack.push(currentIndex);
            }
        }

        return res;
    }
}
