package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;

public class S004_self_count_pair_diff_1 {
    public static int countPairsWithDifference(int[] arr, int K) {
        // Step 1: Create a frequency map
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Count pairs
        int count = 0;
        for (int num : arr) {
            // Check for pairs with (num + K)
            if (frequencyMap.containsKey(num + K)) {
                count += frequencyMap.get(num + K);
            }
            // Check for pairs with (num - K)
            if (K != 0 && frequencyMap.containsKey(num - K)) {
                count += frequencyMap.get(num - K);
            }
        }

        // Step 3: Return the total count (already doubled in the loop)
        // Since pairs are not distinct, every valid pair is counted twice
        return count / 2; // Divide by 2 to avoid double counting
    }
}

