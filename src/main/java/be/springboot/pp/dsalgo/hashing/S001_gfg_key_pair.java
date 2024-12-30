package be.springboot.pp.dsalgo.hashing;

import java.util.HashSet;

public class S001_gfg_key_pair {
    private static boolean findTwoSum(int[] a, int K) {
        HashSet<Integer> seen = new HashSet<>();

        boolean found = false;

        for (int num : a) {
            int complement = K - num;

            if (seen.contains(complement)) { // check for complement
                found = true;
                break;
            }

            seen.add(num); // add current number
        }

        return found;
    }
}
