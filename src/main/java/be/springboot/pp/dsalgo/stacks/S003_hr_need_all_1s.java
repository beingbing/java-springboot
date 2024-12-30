package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S003_hr_need_all_1s {
    public int maximalRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix.length;    // Number of rows
        int m = matrix[0].length; // Number of columns

        int[] heights = new int[m]; // Array to store column heights
        int maxArea = 0;

        for (int[] ints : matrix) {
            for (int j = 0; j < m; j++) // Update heights based on the current row
                if (ints[j] == 1) heights[j]++;
                else heights[j] = 0;

            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            // Maintain a monotonic stack
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
