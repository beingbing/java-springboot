package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S002_lc_0084 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // Arrays to store boundaries
        int[] pse = new int[n];
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) { // Find the previous smaller element
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear(); // Clear stack for right boundary computation

        // Monotonic stack for right boundary
        for (int i = n - 1; i >= 0; i--) { // Find the next smaller element
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int maxArea = 0; // Compute the largest rectangle area
        for (int i = 0; i < n; i++) {
            int width = nse[i] - pse[i] - 1; // Width of the rectangle
            int area = heights[i] * width;     // Area of the rectangle
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}

class LargestRectangleHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;

        // Arrays to store the indices of the next smaller element (NSE) and previous smaller element (PSE)
        int[] nse = new int[n];
        int[] pse = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) { // Compute PSE for each bar
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) pse[stack.pop()] = i;
            stack.push(i);
        }

        while (!stack.isEmpty()) pse[stack.pop()] = -1;

        stack.clear();

        for (int i = 0; i < n; i++) { // Compute NSE for each bar
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) nse[stack.pop()] = i;
            stack.push(i);
        }

        while (!stack.isEmpty()) nse[stack.pop()] = n; // Remaining elements in the stack do not have a next smaller element, so set their NSE to n

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nse[i] - pse[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
