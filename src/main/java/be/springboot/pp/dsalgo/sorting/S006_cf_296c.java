package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S006_cf_296c {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nmk = br.readLine().trim().split("\\s+");
        // Input: array size, operations, and queries count
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        // Input: initial array
        long[] array = new long[n + 1]; // 1-based indexing
        String[] elements = br.readLine().trim().split("\\s+");
        for (int i = 1; i <= n; i++) array[i] = Integer.parseInt(elements[i-1]);

        // Input: operations
        int[] l = new int[m + 1];
        int[] r = new int[m + 1];
        long[] d = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            String[] lrd = br.readLine().trim().split("\\s+");
            l[i] = Integer.parseInt(lrd[0]);
            r[i] = Integer.parseInt(lrd[1]);
            d[i] = Long.parseLong(lrd[2]);
        }

        // Step 1: Process queries
        long[] queryEffect = new long[m + 2]; // Difference array for queries
        for (int i = 0; i < k; i++) {
            String[] xy = br.readLine().trim().split("\\s+");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            queryEffect[x]++;
            queryEffect[y + 1]--;
        }

        // Prefix sum to calculate how many times each operation is applied
        long[] operationCount = new long[m + 1];
        for (int i = 1; i <= m; i++) operationCount[i] = operationCount[i - 1] + queryEffect[i];

        // Step 2: Process operations
        long[] operationEffect = new long[n + 2]; // Difference array for operations
        for (int i = 1; i <= m; i++) {
            long multiplier = operationCount[i];
            operationEffect[l[i]] += d[i] * multiplier;
            operationEffect[r[i] + 1] -= d[i] * multiplier;
        }

        // Prefix sum to apply operation effects to the array
        long[] result = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = array[i] + operationEffect[i];
            if (i > 1) result[i] += result[i - 1] - array[i - 1];
        }

        StringBuilder output = new StringBuilder();
        // Output the final array
        for (int i = 1; i <= n; i++)
            output.append(result[i]).append(" ");
        output.append("\n");

        bw.write(output.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
