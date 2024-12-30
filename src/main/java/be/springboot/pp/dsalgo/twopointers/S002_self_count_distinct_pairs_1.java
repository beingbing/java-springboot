package be.springboot.pp.dsalgo.twopointers;

import java.util.HashSet;

public class S002_self_count_distinct_pairs_1 {
    public static int countDistinctPairs(int[] arr, int K) {
        HashSet<Integer> visited = new HashSet<>();
        HashSet<String> pairs = new HashSet<>();

        int count = 0;

        for (int num : arr) {
            int complement = K - num;

            // Check if complement exists in visited
            if (visited.contains(complement)) {
                // Generate a unique pair representation
                String pair = Math.min(num, complement) + "," + Math.max(num, complement);

                if (!pairs.contains(pair)) {
                    pairs.add(pair); // Add to pairs set to ensure distinctness
                    count++;
                }
            }

            visited.add(num); // Mark the current number as visited
        }

        return count;
    }
}
