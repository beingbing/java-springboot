package be.springboot.pp.dsalgo.bitmanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S002_cf_424c {
    public static int computeQ(int n, int[] p) {
        // Step 1: Precompute the prefix XOR for indices [0, n]
        int[] prefixXor = new int[n + 1];
        for (int i = 1; i <= n; i++) prefixXor[i] = prefixXor[i - 1] ^ i; // Compute XOR from 1 to i

        // Step 2: Compute XOR for all elements in the input array
        int resultXor = 0; // This will hold the final result Q
        for (int value : p) resultXor ^= value; // XOR all input values

        // Step 3: Compute the contribution from modular patterns
        for (int i = 1; i <= n; i++) {
            // Contribution from full cycles
            if ((n / i) % 2 == 1) // If full cycles count is odd, include remaining full cycle XOR
                resultXor ^= prefixXor[i - 1];

            // Contribution from the remaining terms
            resultXor ^= prefixXor[n % i];
        }

        return resultXor; // Return the computed Q
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        String[] input = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(input[i]);

        bw.write(computeQ(n, a) + "\n"); // Output Q
        bw.flush();
        bw.close();
        br.close();
    }
}
