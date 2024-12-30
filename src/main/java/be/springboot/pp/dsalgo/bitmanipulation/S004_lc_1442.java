package be.springboot.pp.dsalgo.bitmanipulation;

import java.util.HashMap;

public class S004_lc_1442 {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        int count = 0;
        int prefixXOR = 0;

        HashMap<Integer, Integer> xorCount = new HashMap<>(); // tracks frequency of a prefix XOR value
        HashMap<Integer, Integer> xorSum = new HashMap<>(); // tracks the sum of indices where a prefix XOR value was seen already

        // keeping prefixXOR as 1-based and keeping 0 index booked for prefixXOR value as 0
        xorCount.put(0, 1); // a 0 will always be seen at index 0 in prefixXOR
        xorSum.put(0, 0); // index sum for seen 0 is summed to 0 and thus started with it

        for (int k = 0; k < n; k++) {
            prefixXOR ^= arr[k];

            // If prefixXOR was seen before, prefixXOR[i-1] = prefixXOR[k]
            if (xorCount.containsKey(prefixXOR)) count += xorCount.get(prefixXOR) * k - xorSum.get(prefixXOR);

            xorCount.put(prefixXOR, xorCount.getOrDefault(prefixXOR, 0) + 1);
            xorSum.put(prefixXOR, xorSum.getOrDefault(prefixXOR, 0) + k + 1);
        }

        return count;
    }
}
