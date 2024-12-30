package be.springboot.pp.dsalgo.bitmanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S002_gfg_find_mis_n_repeat {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        String[] input = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(input[i]);

        int[] ans = findMissingAndRepeated(arr, n);
        result.append(ans[0]).append(" ").append(ans[1]).append("\n");

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] findMissingAndRepeated(int[] arr, int n) {
        long expectedSum = (long) n * (n + 1) / 2; // A + B
        long expectedSquareSum = (long) n * (n + 1) * (2L * n + 1) / 6; // A^2 + B^2

        long actualSum = 0;
        long actualSquareSum = 0;

        for (int num : arr) {
            actualSum += num; // B + B
            actualSquareSum += (long) num * num; // B^2 + B^2
        }

        long X = actualSum - expectedSum; // B - A
        long Y = (actualSquareSum - expectedSquareSum) / X; // B + A

        int repeated = (int) (X + Y) / 2; // B
        int missing = (int) (Y - X) / 2; // A

        return new int[]{repeated, missing};
    }
}
