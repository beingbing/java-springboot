package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S004_cf_816b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nkq = br.readLine().trim().split("\\s+");
        // Input: number of recipes, threshold k, number of queries
        int n = Integer.parseInt(nkq[0]);
        int k = Integer.parseInt(nkq[1]);
        int q = Integer.parseInt(nkq[2]);

        int MAX_TEMP = 200000; // Maximum possible temperature
        int[] freq = new int[MAX_TEMP + 2]; // Frequency array

        // Read recipes and update frequency array
        for (int i = 0; i < n; i++) {
            String[] temps = br.readLine().trim().split("\\s+");
            int l = Integer.parseInt(temps[0]);
            int r = Integer.parseInt(temps[1]);
            freq[l]++;
            freq[r + 1]--;
        }

        // Compute cumulative frequency (number of recipes recommending each temperature)
        int[] count = new int[MAX_TEMP + 1];
        for (int i = 1; i <= MAX_TEMP; i++) {
            count[i] = count[i - 1] + freq[i];
        }

        // Compute admissible temperatures (at least k recommendations)
        int[] admissible = new int[MAX_TEMP + 1];
        for (int i = 1; i <= MAX_TEMP; i++) {
            admissible[i] = count[i] >= k ? 1 : 0;
        }

        // Build prefix sum of admissible temperatures
        int[] admissibleSum = new int[MAX_TEMP + 1];
        for (int i = 1; i <= MAX_TEMP; i++) {
            admissibleSum[i] = admissibleSum[i - 1] + admissible[i];
        }

        // Process queries
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String[] temps = br.readLine().trim().split("\\s+");
            int a = Integer.parseInt(temps[0]);
            int b = Integer.parseInt(temps[1]);
            // Compute result using prefix sum
            int result = admissibleSum[b] - admissibleSum[a - 1];
            output.append(result).append("\n");
        }

        bw.write(output.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
