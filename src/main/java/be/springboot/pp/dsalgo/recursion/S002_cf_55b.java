package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S002_cf_55b {
    int minResult = Integer.MAX_VALUE;

    public int smallestNumber(int a, int b, int c, int d, char[] ops) {
        List<Integer> nums = Arrays.asList(a, b, c, d);
        backtrack(nums, ops, 0);
        return minResult;
    }

    private void backtrack(List<Integer> nums, char[] ops, int opIndex) {
        if (nums.size() == 1) {
            minResult = Math.min(minResult, nums.get(0));
            return;
        }

        // Try each pair of numbers for current operation
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                List<Integer> newNums = new ArrayList<>();
                int result = applyOperation(nums.get(i), nums.get(j), ops[opIndex]);

                // Prepare the list for the next recursive step
                for (int k = 0; k < nums.size(); k++)
                    if (k != i && k != j) newNums.add(nums.get(k));
                newNums.add(result);

                backtrack(newNums, ops, opIndex + 1);
            }
        }
    }

    private int applyOperation(int x, int y, char op) {
        if (op == '+') return x + y;
        return x * y;
    }

    public static void main(String[] args) {
        S002_cf_55b solver = new S002_cf_55b();
        System.out.println(solver.smallestNumber(1, 1, 1, 1, new char[]{'+', '+', '*'})); // Expected output: 3
        System.out.println(solver.smallestNumber(2, 2, 2, 2, new char[]{'*', '*', '+'})); // Expected output: 8
        System.out.println(solver.smallestNumber(1, 2, 3, 4, new char[]{'*', '+', '+'})); // Expected output: 9
    }
}
