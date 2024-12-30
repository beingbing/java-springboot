package be.springboot.pp.dsalgo.bitmanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S003_lc_1734 {
    private static int[] decode(int[] encoded, int n) {
        // Step 1: Compute total XOR for all numbers from 1 to n
        int totalXOR = 0;
        for (int i = 1; i <= n; i++) totalXOR ^= i;

        // Step 2: Compute partial XOR for every second element in encoded
        int partialXOR = 0;
        for (int i = 1; i < encoded.length; i += 2) partialXOR ^= encoded[i];

        // Step 3: Calculate the first element of perm
        int[] perm = new int[n];
        perm[0] = totalXOR ^ partialXOR;

        // Step 4: Reconstruct the remaining elements of perm
        for (int i = 0; i < encoded.length; i++) perm[i + 1] = perm[i] ^ encoded[i];

        return perm;
    }
}
