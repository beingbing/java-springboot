package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;

public class S002_gfg_count_pairs_1 {
    public int countPairsWithSum(int[] a, int N, int K) {
        HashMap<Integer, Integer> numFreq = new HashMap<>();
        int pairCount = 0;

        for (int num : a) {
            int complement = K - num;
            if (numFreq.containsKey(complement)) pairCount += numFreq.get(complement);
            numFreq.put(num, numFreq.getOrDefault(num, 0) + 1);
        }

        return pairCount;
    }
}
