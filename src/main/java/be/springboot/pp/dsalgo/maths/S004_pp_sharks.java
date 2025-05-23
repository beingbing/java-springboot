package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class S004_pp_sharks {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> mainDiag = new HashMap<>();
        Map<Integer, Integer> antiDiag = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // Count bishops on the same main and anti-diagonals
            mainDiag.put(x - y, mainDiag.getOrDefault(x - y, 0) + 1);
            antiDiag.put(x + y, antiDiag.getOrDefault(x + y, 0) + 1);
        }

        long result = 0;

        for (int count : mainDiag.values()) result += (long) count * (count - 1) / 2;
        for (int count : antiDiag.values()) result += (long) count * (count - 1) / 2;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
