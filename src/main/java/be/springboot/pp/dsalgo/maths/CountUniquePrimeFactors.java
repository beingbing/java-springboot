package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CountUniquePrimeFactors {
    static int MAX = 1000000;
    static int[] spf = null;

    private static void processSpfTill(int n) {
        spf = new int[n + 1];

        for (int i = 0; i <= n; i++) spf[i] = i;

        for (int p = 2; p * p <= n; p++)
            if (spf[p] == p)
                for (int multiple = p * p; multiple <= n; multiple += p)
                    if (spf[multiple] == multiple) spf[multiple] = p;
    }

    private static int countUniquePrimeFactors(int x) {
        int divisorsCount = 0;
        while (x != 1) {
            int prime = spf[x];
            while (x % prime == 0) x /= prime;
            divisorsCount++;
        }
        return divisorsCount;
    }

    public static void main(String[] args) throws IOException {
        processSpfTill(MAX);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] inputs = br.readLine().trim().split(" ");
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n; i++) {
                int temp = Integer.parseInt(inputs[i]);
                int count = countUniquePrimeFactors(temp);
                sb.append(count).append(" ");
            }

            bw.write(sb.toString().trim() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
