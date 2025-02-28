package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class PowerSetLexicographical {

    public static void main(String[] args) {
        PowerSetLexicographical solver = new PowerSetLexicographical();
        int[] nums = {1, 2, 3, 4};
        int key = 6;
        List<List<Integer>> result = solver.findSubsets(nums, key);
        System.out.println(result);

        result = solver.generateSubsets(nums, key);
        System.out.println(result);
    }

    private void explore(int[] arr, int index, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > target) break; // Optimization: Stop if the number is greater than remaining sum
            current.add(arr[i]);
            explore(arr, i + 1, target - arr[i], current, result);
            current.removeLast();
        }
    }

    private List<List<Integer>> findSubsets(int[] arr, int key) {
        List<List<Integer>> result = new ArrayList<>();
        explore(arr, 0, key, new ArrayList<>(), result);
        return result;
    }

    public List<List<Integer>> generateSubsets(int[] a, int key) {
        List<List<Integer>> result = new ArrayList<>();
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
        generateSubsets(a, index + 1, target - a[index], currentSubset, result);
        currentSubset.removeLast();
    }
}
