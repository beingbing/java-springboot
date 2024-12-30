package be.springboot.pp.dsalgo.stacks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class S003_cf_548d {
    public int[] calculateMaxStrength(int n, int[] heights) {
        int[] left = new int[n];  // Left bounds
        int[] right = new int[n]; // Right bounds
        int[] result = new int[n]; // Result for max strengths

        // Monotonic stack for left bounds
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Monotonic stack for right bounds
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Calculate maximum strength for each group size
        int[] maxStrength = new int[n + 1]; // Temporary array for max strengths
        for (int i = 0; i < n; i++) {
            int size = right[i] - left[i] - 1; // Size of group where heights[i] is the minimum
            maxStrength[size] = Math.max(maxStrength[size], heights[i]);
        }

        // Propagate maximum strengths backward
        for (int i = n - 1; i >= 1; i--) maxStrength[i] = Math.max(maxStrength[i], maxStrength[i + 1]);

        for (int i = 1; i <= n; i++) result[i-1] = maxStrength[i];

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        S003_cf_548d solution = new S003_cf_548d();

        // Example Input
        int n = Integer.parseInt(br.readLine().trim());
        int[] heights = Arrays.stream(br.readLine().trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[] ans = solution.calculateMaxStrength(n, heights);
        for (int strength : ans) {
            result.append(strength).append(" ");
        }

        bw.write(result.toString().trim());
        bw.flush();
        bw.close();
        br.close();
    }
}
