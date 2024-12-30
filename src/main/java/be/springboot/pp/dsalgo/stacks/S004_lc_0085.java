package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S004_lc_0085 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols]; // Array to store histogram heights
        int maxArea = 0;

        // Iterate over each row
        for (int i = 0; i < rows; i++) {
            // Update histogram heights based on the current row
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++; // Increment height if the cell is '1'
                } else {
                    heights[j] = 0; // Reset height if the cell is '0'
                }
            }

            // Calculate the largest rectangle for the current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // Helper method to calculate the largest rectangle area in a histogram
    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            // Maintain a decreasing stack
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()]; // Height of the bar
                int width = stack.isEmpty() ? i : i - stack.peek() - 1; // Width of the rectangle
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i); // Push current index onto the stack
        }

        return maxArea;
    }
}
