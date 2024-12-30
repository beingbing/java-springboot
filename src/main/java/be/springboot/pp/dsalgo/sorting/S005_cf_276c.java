package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S005_cf_276c {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nq = br.readLine().trim().split("\\s+");
        // Input: number of elements and queries
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);

        // Input: the array
        int[] a = new int[n];
        String[] elements = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(elements[i]);

        // Frequency array for range updates
        int[] freq = new int[n + 2]; // Extra space for range updates

        // Read queries and update frequency array
        for (int i = 0; i < q; i++) {
            String[] range = br.readLine().trim().split("\\s+");
            int l = Integer.parseInt(range[0]);
            int r = Integer.parseInt(range[1]);
            freq[l]++;
            freq[r + 1]--;
        }

        // Compute prefix sum to get frequency of each index
        int[] actualFreq = new int[n];
        actualFreq[0] = freq[1]; // Adjust for 1-based indexing
        for (int i = 1; i < n; i++) actualFreq[i] = actualFreq[i - 1] + freq[i + 1];

        // Sort the array and the frequency array in descending order
        Arrays.sort(a);
        Arrays.sort(actualFreq);

        // Compute maximum sum by pairing largest values with highest frequencies
        long maxSum = 0;
        for (int i = 0; i < n; i++) maxSum += (long) a[i] * actualFreq[i];

        // Output the result
        bw.write(maxSum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
