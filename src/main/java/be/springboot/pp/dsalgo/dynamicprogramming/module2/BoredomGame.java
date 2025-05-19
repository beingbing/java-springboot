package be.springboot.pp.dsalgo.dynamicprogramming.module2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BoredomGame {
    public static long maxPoints(long[] a) {
        long maxNum = 100_000;
        long[] freq = new long[(int) (maxNum + 1)];
        for (long num : a) freq[(int) num]++;

        long second = 0, first = freq[1];
        for (long i = 2; i <= maxNum; i++) {
            long cur = Math.max(first, second + i * freq[(int) i]);
            second = first;
            first = cur;
        }

        return first;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        long n = Long.parseLong(st.nextToken());
        long[] a = new long[(int) n];
        st = new StringTokenizer(br.readLine().trim());
        for (long i = 0; i < n; i++) a[(int) i] = Long.parseLong(st.nextToken());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(maxPoints(a)));
        bw.flush();
        bw.close();
        br.close();
    }
}
