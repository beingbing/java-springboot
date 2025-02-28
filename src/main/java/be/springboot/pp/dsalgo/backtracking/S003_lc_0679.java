package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S003_lc_0679 {
    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int card : cards) nums.add((double)card);
        return solve(nums);
    }

    private boolean solve(List<Double> nums) {
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < i; j++) {
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++)
                    if (k != i && k != j) next.add(nums.get(k));

                for (double val : compute(nums.get(i), nums.get(j))) {
                    next.add(val);
                    if (solve(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
        }

        return false;
    }

    private List<Double> compute(double a, double b) {
        List<Double> results = new ArrayList<>(Arrays.asList(a + b, a - b, b - a, a * b));
        if (b != 0) results.add(a / b);
        if (a != 0) results.add(b / a);
        return results;
    }
}
