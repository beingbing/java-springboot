package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class S005_cf_276c {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nq = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);

        Integer[] a = new Integer[n];
        String[] elements = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(elements[i]);

        int[] freq = new int[n + 2]; // Extra space for range updates

        for (int i = 0; i < q; i++) {
            String[] range = br.readLine().trim().split("\\s+");
            int l = Integer.parseInt(range[0]);
            int r = Integer.parseInt(range[1]);
            freq[l]++;
            freq[r + 1]--;
        }

        Integer[] actualFreq = new Integer[n];
        actualFreq[0] = freq[1]; // Adjust for 1-based indexing
        for (int i = 1; i < n; i++) actualFreq[i] = actualFreq[i - 1] + freq[i + 1];

        // Sort the array and the frequency array in descending order
        Arrays.sort(a, Collections.reverseOrder());
        Arrays.sort(actualFreq, Collections.reverseOrder());

        // Compute maximum sum by pairing largest values with highest frequencies
        long maxSum = 0;
        for (int i = 0; i < n; i++) maxSum += (long) a[i] * actualFreq[i];

        bw.write(maxSum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
