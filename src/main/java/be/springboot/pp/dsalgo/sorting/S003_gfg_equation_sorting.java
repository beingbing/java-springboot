package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class S003_gfg_equation_sorting {
    public ArrayList<Integer> sortArray(int[] a, int A, int B, int C) {
        int n = a.length;
        int left = 0, right = n - 1;
        int fill = (A < 0) ? 0 : n - 1;
        int[] ans = new int[n];

        while (left <= right) {
            int leftVal = applyQuadratic(A, B, C, a[left]);
            int rightVal = applyQuadratic(A, B, C, a[right]);

            if (A < 0) {
                if (leftVal < rightVal) {
                    ans[fill++] = leftVal;
                    left++;
                } else {
                    ans[fill++] = rightVal;
                    right--;
                }
            } else {
                if (leftVal < rightVal) {
                    ans[fill--] = rightVal;
                    right--;
                } else {
                    ans[fill--] = leftVal;
                    left++;
                }
            }
        }

        return IntStream.of(ans).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    private int applyQuadratic(int A, int B, int C, int x) {
        return A * x * x + B * x + C;
    }
}
