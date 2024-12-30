package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S003_cc_minvote {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder result = new StringBuilder();

        // Number of test cases
        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            // Input the number of minions
            int N = Integer.parseInt(br.readLine().trim());
            long[] S = new long[N];
            String[] input = br.readLine().trim().split("\\s+");

            for (int i = 0; i < N; i++) S[i] = Long.parseLong(input[i]);

            long[] votes = getVotesCount(N, S);

            for (int i = 0; i < N; i++) result.append(votes[i]).append(" ");
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static long[] getVotesCount(int n, long[] a) {
        long[] votes = new long[n];
        long sum = 0;

        // Calculate votes polled to the right
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i + 1; j < n; j++) {
                if (a[i] >= sum) votes[j]++;
                else break;
                sum += a[j];
            }
        }

        // Calculate votes polled to the left
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (a[i] >= sum) votes[j]++;
                else break;
                sum += a[j];
            }
        }

        return votes;
    }
}
