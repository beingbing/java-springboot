package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S006_lc_1250 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken());
            int[] A = new int[n];

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());

            int gcd = A[0];
            for (int i = 1; i < n; i++) {
                gcd = gcd(gcd, A[i]);
                if (gcd == 1) break; // early exit if gcd is already 1
            }

            sb.append(gcd == 1 ? "true\n" : "false\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
