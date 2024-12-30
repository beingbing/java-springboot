package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;

public class S002_gfg_count_pairs_1 {
    public static int countPairsWithSum(int[] arr, int N, int K) {
        // Hash map to store the frequency of elements
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int pairCount = 0;

        for (int num : arr) {
            // Find the complement
            int complement = K - num;

            // If the complement exists, add its frequency to the pair count
            if (countMap.containsKey(complement)) pairCount += countMap.get(complement);

            // Add the current number to the hash map or increment its count
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        return pairCount;
    }
}
