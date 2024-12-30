package be.springboot.pp.dsalgo.bitmanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S001_cc_ptmssng {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int missingX = 0, missingY = 0;
            String[] input;

            // Read 4N-1 points
            for (int i = 0; i < 4 * N - 1; i++) {
                input = br.readLine().trim().split("\\s+");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);

                // XOR the x and y coordinates
                missingX ^= x;
                missingY ^= y;
            }

            result.append(missingX).append(" ").append(missingY).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
