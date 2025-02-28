package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
    public List<List<Integer>> generateSubsets(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(new ArrayList<>(), result, a, 0);
        return result;
    }

    // pure recursion
    private void generateSubsets(List<Integer> currentSubset, List<List<Integer>> result, int[] a, int index) {
        if (index == a.length) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }
        // Exclude current element
        generateSubsets(currentSubset, result, a, index + 1);

        // Include current element
        currentSubset.add(a[index]);
        generateSubsets(currentSubset, result, a, index + 1);
        currentSubset.removeLast();
    }

    public List<List<Integer>> findSubsets(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        findSubsets(new ArrayList<>(), result, a, 0);
        return result;
    }

    // iterative recursion
    private void findSubsets(List<Integer> currentSubset, List<List<Integer>> result, int[] a, int index) {
        result.add(new ArrayList<>(currentSubset));

        for (int i = index; i < a.length; i++) {
            currentSubset.add(a[i]);
            findSubsets(currentSubset, result, a, i + 1);
            currentSubset.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 2};
        PowerSet solver = new PowerSet();
        List<List<Integer>> result = solver.generateSubsets(a);
        System.out.println(result);

        result = solver.findSubsets(a);
        System.out.println(result);
    }
}
