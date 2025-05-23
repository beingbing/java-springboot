package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S007_cf_617b {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken()); // number of chocolate pieces
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

        List<Integer> nutIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) if (a[i] == 1) nutIndices.add(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        if (nutIndices.isEmpty()) sb.append(0).append("\n"); // No nuts => not possible
        else if (nutIndices.size() == 1) sb.append(1).append("\n"); // Only 1 nut => only one valid part
        else {
            long result = 1;
            for (int i = 1; i < nutIndices.size(); i++) {
                int gap = nutIndices.get(i) - nutIndices.get(i - 1);
                result *= gap;
            }
            sb.append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
