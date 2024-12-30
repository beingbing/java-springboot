package be.springboot.pp.dsalgo.sorting;

import java.util.Scanner;

public class S003_gfg_equation_sorting {
    // Function to compute sorted transformed array
    private static int[] sortedTransformedArray(int[] arr, int A, int B, int C) {
        int N = arr.length;
        int[] result = new int[N];
        int left = 0, right = N - 1;
        int index = (A > 0) ? N - 1 : 0;  // Start filling from the end if A > 0

        // Two-pointer approach
        while (left <= right) {
            int leftVal = applyQuadratic(arr[left], A, B, C);
            int rightVal = applyQuadratic(arr[right], A, B, C);

            if (A > 0) {
                // For upward parabola, place larger values at the end
                if (leftVal > rightVal) {
                    result[index--] = leftVal;
                    left++;
                } else {
                    result[index--] = rightVal;
                    right--;
                }
            } else {
                // For downward parabola, place smaller values at the start
                if (leftVal < rightVal) {
                    result[index++] = leftVal;
                    left++;
                } else {
                    result[index++] = rightVal;
                    right--;
                }
            }
        }
        return result;
    }

    // Apply the quadratic transformation
    private static int applyQuadratic(int x, int A, int B, int C) {
        return A * x * x + B * x + C;
    }
}
