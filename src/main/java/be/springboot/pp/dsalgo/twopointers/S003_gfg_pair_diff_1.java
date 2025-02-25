package be.springboot.pp.dsalgo.twopointers;

import java.util.HashSet;

public class S003_gfg_pair_diff_1 {
    public String hasPairWithDifference(int[] arr, int N, int K) {
        HashSet<Integer> seen = new HashSet<>();

        for (int num : arr) {
            if (seen.contains(num + K) || seen.contains(num - K)) return "Yes";
            seen.add(num);
        }

        return "No";
    }
}
