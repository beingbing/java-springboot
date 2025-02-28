package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationsWithDuplicates {
    public List<String> permuteUnique(String s) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : s.toCharArray()) charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        backtrack(result, new StringBuilder(), charCountMap, s.length());
        return result;
    }

    private void backtrack(List<String> result, StringBuilder currentPermutation, Map<Character, Integer> charCountMap, int targetLength) {
        if (currentPermutation.length() == targetLength) {
            result.add(currentPermutation.toString());
            return;
        }

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();
            if (count <= 0) continue; // Use character if available
            currentPermutation.append(ch);
            charCountMap.put(ch, count - 1); // Reduce frequency
            backtrack(result, currentPermutation, charCountMap, targetLength);
            currentPermutation.deleteCharAt(currentPermutation.length() - 1);
            charCountMap.put(ch, count);

        }
    }

    public static void main(String[] args) {
        PermutationsWithDuplicates permuter = new PermutationsWithDuplicates();
        System.out.println(permuter.permuteUnique("aabc"));
        // Expected output: All unique permutations like ["aabc", "aacb", "abac", ...]
    }
}
