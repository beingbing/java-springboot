package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S002_cf_1174b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        String[] input = br.readLine().trim().split("\\s+");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(input[i]);

        boolean hasOdd = false, hasEven = false;

        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) hasEven = true;
            else hasOdd = true;
            if (hasOdd && hasEven) break;
        }

        // Sort the array only if it contains both odd and even numbers
        if (hasOdd && hasEven) Arrays.sort(arr);

        StringBuilder result = new StringBuilder();

        for (int num : arr) result.append(num).append(" ");
        result.append("\n");

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
