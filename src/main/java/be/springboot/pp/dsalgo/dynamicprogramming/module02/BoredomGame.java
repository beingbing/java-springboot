package be.springboot.pp.dsalgo.dynamicprogramming.module02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BoredomGame {
    public static long maxPoints(long[] a) {
        long maxNum = Integer.MIN_VALUE;
        long[] freq = new long[100001];
        for (long num : a) {
            freq[(int) num]++;
            maxNum = Math.max(maxNum, num);
        }

        long pen = 0, pre = freq[1] * 1, cur; // penultimate, previous, current
        for (long i = 2; i <= maxNum; i++) {
            cur = Math.max(pre, pen + freq[(int)i] * i);
            pen = pre;
            pre = cur;
        }

        return pre;
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
