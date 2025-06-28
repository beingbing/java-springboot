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

    public int findDistinctDiffPairs(int[] a, int k) {
        HashSet<Integer> seen = new HashSet<>();
        HashSet<String> pairs = new HashSet<>();

        for (int ele : a) {
            int compliment = ele + k;
            evaluate(seen, pairs, ele, compliment);

            compliment = ele > k ? ele - k : k - ele;
            evaluate(seen, pairs, ele, compliment);

            seen.add(ele);
        }

        return pairs.size();
    }

    private void evaluate(HashSet<Integer> seen, HashSet<String> pairs, int ele, int compliment) {
        if (!seen.contains(compliment)) return;
        int fst = Math.min(ele, compliment);
        int sec = Math.max(ele, compliment);
        String pair = fst + "," + sec;
        pairs.add(pair);
    }
}
