package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] a = {2, 3, 5};
        int key = 8;

        CombinationSum solver = new CombinationSum();
        List<List<Integer>> result = solver.findCombinations(a, key);
        System.out.println(result);

        result = solver.generateSubsets(a, key);
        System.out.println(result);
    }

    private List<List<Integer>> findCombinations(int[] a, int key) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a); // Ensure lexicographical order
        explore(a, 0, key, new ArrayList<>(), result);
        return result;
    }

    private static void explore(int[] a, int index, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < a.length; i++) {
            if (a[i] > target) break; // Optimization: Stop if element is greater than remaining sum
            current.add(a[i]);
            explore(a, i, target - a[i], current, result); // Allow reuse of same element
            current.removeLast();
        }
    }

    public List<List<Integer>> generateSubsets(int[] a, int key) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a);
        generateSubsets(a, 0, key, new ArrayList<>(), result);
        return result;
    }

    // pure recursion
    private void generateSubsets(int[] a, int index, int target, List<Integer> currentSubset, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }

        if (index >= a.length || target < 0) return;

        // Exclude current element
        generateSubsets(a, index + 1, target, currentSubset, result);

        // Include current element
        currentSubset.add(a[index]);
        generateSubsets(a, index, target - a[index], currentSubset, result);
        currentSubset.removeLast();
    }
}