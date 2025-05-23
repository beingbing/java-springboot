package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S008_cf_152c {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] names = new String[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            names[i] = st.nextToken();
        }

        long result = 1;

        for (int col = 0; col < m; col++) {
            Set<Character> uniqueChars = new HashSet<>();
            for (int row = 0; row < n; row++) uniqueChars.add(names[row].charAt(col));
            result = (result * uniqueChars.size()) % MOD;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
