package be.springboot.pp.dsalgo.twopointers;

import java.util.HashSet;

public class S004_gfg_distinct_pair_diff_1 {
    public static int countPairsWithDifference(int[] arr, int K) {
        HashSet<Integer> visited = new HashSet<>();
        HashSet<String> uniquePairs = new HashSet<>();
        int count = 0;

        for (int num : arr) {
            // Check if pairs with the difference K exist
            if (visited.contains(num + K)) {
                String pair = Math.min(num, num + K) + "," + Math.max(num, num + K);
                if (!uniquePairs.contains(pair)) {
                    uniquePairs.add(pair);
                    count++;
                }
            }
            if (visited.contains(num - K)) {
                String pair = Math.min(num, num - K) + "," + Math.max(num, num - K);
                if (!uniquePairs.contains(pair)) {
                    uniquePairs.add(pair);
                    count++;
                }
            }
            visited.add(num);
        }

        return count;
    }
}
