package be.springboot.pp.dsalgo.narytrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S001_cf_697c {
    static Map<Long, Long> fees = new HashMap<>();

    private static void updateFees(long u, long v, long w) {
        while (u != v) {
            if (u > v) {
                fees.put(u, fees.getOrDefault(u, 0L) + w);
                u /= 2; // Move from u to v
            } else {
                fees.put(v, fees.getOrDefault(v, 0L) + w);
                v /= 2; // Move from v to u
            }
        }
    }

    private static long calculateFees(long u, long v) {
        long total = 0;
        while (u != v) {
            if (u > v) {
                total += fees.getOrDefault(u, 0L);
                u /= 2; // Move u to its parent
            } else {
                total += fees.getOrDefault(v, 0L);
                v /= 2; // Move v to its parent
            }
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine().trim());

        List<Long> results = new ArrayList<>();
        String[] input;

        for (int i = 0; i < q; i++) {
            input = br.readLine().trim().split("\\s+");
            int type = Integer.parseInt(input[0]);

            if (type == 1) {
                long v = Long.parseLong(input[1]);
                long u = Long.parseLong(input[2]);
                long w = Long.parseLong(input[3]);
                updateFees(u, v, w);
            } else if (type == 2) {
                long v = Long.parseLong(input[1]);
                long u = Long.parseLong(input[2]);
                results.add(calculateFees(u, v));
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();
        for (long res : results) result.append(res).append("\n");

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
