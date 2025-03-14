package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S003_cc_minvote_2 {
    private static long[] getVotesCount(long[] influence, int n) {
        long[] prefixSum = computePrefixSum(influence, n);
        long[] votes = new long[n];

        for (int i = 0; i < n - 1; i++) {
            int j = findRight(i, n, influence[i], prefixSum);
            votes[i + 1]++;
            if (j < n) votes[j]--;
        }

        for (int j = n - 1; j > 0; j--) {
            int i = findLeft(0, j-1, influence[j], prefixSum);
            votes[i]++;
            votes[j]--;
        }

        for (int i = 1; i < n; i++) votes[i] += votes[i - 1];
        return votes;
    }

    private static long[] computePrefixSum(long[] influence, int n) {
        long[] prefixSum = new long[n];
        prefixSum[0] = influence[0];
        for (int i = 1; i < n; i++) prefixSum[i] = prefixSum[i - 1] + influence[i];
        return prefixSum;
    }

    private static long rangeSum(long[] prefixSum, int low, int high) {
        if (low > high) return Long.MAX_VALUE;
        return prefixSum[high] - prefixSum[low];
    }

    private static int findLeft(int i, int j, long influence, long[] prefixSum) {
        int low = i, high = j;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (rangeSum(prefixSum, mid, j) <= influence) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }

    private static int findRight(int i, int j, long influence, long[] prefixSum) {
        int low = i + 1, high = j;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (rangeSum(prefixSum, i, mid-1) <= influence) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            long[] S = new long[N];
            String[] input = br.readLine().trim().split("\\s+");

            for (int i = 0; i < N; i++) S[i] = Long.parseLong(input[i]);

            long[] votes = getVotesCount(S, N);

            for (int i = 0; i < N; i++) result.append(votes[i]).append(" ");
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
