package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class S003_lc_0679 {
    private static final double TARGET = 24.0;
    private static final double EPSILON = 1e-6;

    public boolean judgePoint24(int[] cards) {
        // Convert int array to list of doubles
        List<Double> numbers = new ArrayList<>();
        for (int num : cards) {
            numbers.add((double) num);
        }
        // Begin recursive check
        return solve(numbers);
    }

    private boolean solve(List<Double> numbers) {
        // Base case: If we've reduced to one number, check if it's close to 24
        if (numbers.size() == 1) return Math.abs(numbers.get(0) - TARGET) < EPSILON;

        // Try every pair of numbers and apply all operations
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (i != j) {
                    // Pick two different numbers to operate on
                    List<Double> nextRound = new ArrayList<>();
                    for (int k = 0; k < numbers.size(); k++) {
                        if (k != i && k != j) nextRound.add(numbers.get(k));  // Add remaining numbers
                    }

                    // Try all operations between numbers[i] and numbers[j]
                    for (double result : compute(numbers.get(i), numbers.get(j))) {
                        nextRound.add(result);  // Add result to new list
                        if (solve(nextRound)) {
                            return true;  // If any result is valid, return true immediately
                        }
                        nextRound.remove(nextRound.size() - 1);  // Backtrack
                    }
                }
            }
        }
        return false;  // Return false if no valid result found
    }

    // Compute all possible results of operations between two numbers
    private List<Double> compute(double a, double b) {
        List<Double> results = new ArrayList<>();
        results.add(a + b); // Addition
        results.add(a - b); // Subtraction
        results.add(b - a); // Reverse Subtraction
        results.add(a * b); // Multiplication
        if (Math.abs(b) > EPSILON) results.add(a / b); // Division (avoid dividing by zero)
        if (Math.abs(a) > EPSILON) results.add(b / a); // Reverse Division
        return results;
    }
}
