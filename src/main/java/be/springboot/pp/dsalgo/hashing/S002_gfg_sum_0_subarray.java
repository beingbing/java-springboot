package be.springboot.pp.dsalgo.hashing;

import java.util.HashSet;
import java.util.Set;

public class S002_gfg_sum_0_subarray {
    private static boolean hasZeroSumSubarray(int[] arr) {
        Set<Integer> prefixSumSet = new HashSet<>();
        int prefixSum = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum == 0 || prefixSumSet.contains(prefixSum)) return true;

            prefixSumSet.add(prefixSum);
        }

        return false;
    }
}
