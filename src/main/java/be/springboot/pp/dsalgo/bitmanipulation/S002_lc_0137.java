package be.springboot.pp.dsalgo.bitmanipulation;

public class S002_lc_0137 {
    public static int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // Update 'seenOnce' to hold bits appearing exactly once
            seenOnce = (seenOnce ^ num) & ~seenTwice;

            // Update 'seenTwice' to hold bits appearing exactly twice
            seenTwice = (seenTwice ^ num) & ~seenOnce;
        }

        // 'seenOnce' holds the single number
        return seenOnce;
    }
}

class S002_lc_0137_2 {
    public int singleNumber(int[] nums) {
        long mask = 1L << 31;
        int ans = 0;
        // Iterate through 32 bit positions
        while (mask != 0) {
            long bitSum = 0;
            // Calculate the sum of bits for the current bit position
            for (int num : nums) bitSum += (num & mask);
            // Set the bit in the result if the bitSum is not a multiple of 3
            if (bitSum % 3 != 0) ans |= mask;
            mask >>= 1;
        }
        return ans;
    }
}

class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int tempA = (~a & b & c) | (a & ~b & ~c);
            b = (~a & ~b & c) | (~a & b & ~c);
            a = tempA;
        }
        return a | b; // b will be non-zero iff a number appears only twice
    }
}

