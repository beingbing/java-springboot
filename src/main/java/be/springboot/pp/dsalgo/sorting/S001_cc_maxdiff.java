package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S001_cc_maxdiff {
    public int maxWtDiff(int[] weights, int k) {
        int n = weights.length;
        if (n < k) return 0;

        Arrays.sort(weights);

        int total = 0;
        for (int weight : weights) total += weight;

        int kLightest = 0;
        for (int i = 0; i < k; i++) kLightest += weights[i];

        int kHeaviest = 0;
        for (int i = n - 1; i >= n - k; i--) kHeaviest += weights[i];

        int kLightestOther = total - kLightest;
        int kHeaviestOther = total - kHeaviest;

        return Math.max(kHeaviest - kHeaviestOther, kLightestOther - kLightest);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] weights = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) weights[i] = Integer.parseInt(st.nextToken());

            S001_cc_maxdiff solver = new S001_cc_maxdiff();
            int maxDiff = solver.maxWtDiff(weights, K);
            sb.append(maxDiff).append("\n");
        }
        System.out.print(sb);
    }
}
