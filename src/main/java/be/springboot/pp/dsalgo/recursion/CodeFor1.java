package be.springboot.pp.dsalgo.recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CodeFor1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().trim().split("\\s+");

        long n = Long.parseLong(input[0]);
        long l = Long.parseLong(input[1]);
        long r = Long.parseLong(input[2]);

        StringBuilder result = new StringBuilder();

        result.append(countOnes(n, l, r));

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

private static long countOnes(long n, long l, long r) {
    long len = getLength(n);
    return countOnesInRange(n, l, r, 1, len);
}

private static long getLength(long n) {
    if (n == 0 || n == 1) return 1;
    return 2 * getLength(n / 2) + 1;
}

private static long countOnesInRange(long n, long l, long r, long start, long end) {
    if (end < l || r < start) return 0; // Out of range
    if (l <= start && end <= r) return countTotalOnes(n); // Fully within range

    long mid = start + (end - start) / 2;
    long leftCount = countOnesInRange(n / 2, l, r, start, mid - 1);
    long rightCount = countOnesInRange(n / 2, l, r, mid + 1, end);
    long midCount = (l <= mid && mid <= r) ? (n % 2) : 0;

    return leftCount + midCount + rightCount;
}

private static long countTotalOnes(long n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return countTotalOnes(n / 2) * 2 + (n % 2);
}
}
