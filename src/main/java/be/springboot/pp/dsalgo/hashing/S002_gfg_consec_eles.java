package be.springboot.pp.dsalgo.hashing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class S002_gfg_consec_eles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());

            int[] arr = new int[N];
            String[] input = br.readLine().trim().split("\\s+");
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input[i]);

            // Check if the array contains consecutive numbers
            if (isConsecutive(arr)) result.append("Yes\n");
            else result.append("No\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isConsecutive(int[] arr) {
        int n = arr.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        Set<Integer> uniqueElements = new HashSet<>();

        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);

            // Check for duplicates
            if (uniqueElements.contains(num)) return false;
            uniqueElements.add(num);
        }

        return (max - min + 1 == n); // Check if the range matches the number of elements
    }
}

