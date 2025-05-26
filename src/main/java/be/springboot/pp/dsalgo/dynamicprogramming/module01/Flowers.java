package be.springboot.pp.dsalgo.dynamicprogramming.module01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Flowers {
    static final int MOD = 1_000_000_007;
    static int[] dp, prefix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int t = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());

        int MAX_N = 100_000;
        dp = new int[MAX_N + 1];
        prefix = new int[MAX_N + 1];

        dp[0] = 1;
        for (int i = 1; i <= MAX_N; i++) {
            dp[i] = dp[i - 1];
            if (i >= k) dp[i] = (dp[i] + dp[i - k]) % MOD;
        }

        for (int i = 1; i <= MAX_N; i++) // Prefix Sum Precompute
            prefix[i] = (prefix[i - 1] + dp[i]) % MOD;

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) { // Answer Queries in O(1)
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            sb.append((prefix[b] - prefix[a - 1] + MOD) % MOD).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
