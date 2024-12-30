package be.springboot.pp.dsalgo.bitmanipulation;

import java.util.Scanner;

public class S001_gfg_2_subseqs {
    private static final int MOD = 1_000_000_007;

    public int countPowerOf2Subsequences(int[] arr) {
        int count = 0;

        // Count numbers that are powers of 2
        for (int num : arr) if ((num & (num - 1)) == 0) count++;

        // If no valid numbers, return 0
        if (count == 0) return 0;

        // Compute (2^count - 1) % MOD using modular exponentiation
        long result = fastExponentiation(2, count) - 1;
        return (int) result;
    }

    private long fastExponentiation(long n, long k) {
        if (k == 0) return 1; // Base case: anything raised to 0 is 1

        long val = fastExponentiation(n, k / 2); // Recursive call with halved power

        val = (val * val) % MOD;
        if (k % 2 == 0) return val; // if even power, then multiply number with itself
        return ((n % MOD) * val) % MOD; // if odd power, then multiply base once.
    }
}
