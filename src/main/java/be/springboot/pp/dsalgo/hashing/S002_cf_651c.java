package be.springboot.pp.dsalgo.hashing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class S002_cf_651c {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        // Maps to count frequencies
        Map<Integer, Integer> xCounts = new HashMap<>();
        Map<Integer, Integer> yCounts = new HashMap<>();
        Map<String, Integer> pointCounts = new HashMap<>();

        // Read points and count frequencies
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().trim().split("\\s+");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            // Update counts
            xCounts.put(x, xCounts.getOrDefault(x, 0) + 1);
            yCounts.put(y, yCounts.getOrDefault(y, 0) + 1);
            String point = "(" + x + ", " + y + ")";
            pointCounts.put(point, pointCounts.getOrDefault(point, 0) + 1);
        }

        // Calculate valid pairs
        long totalPairs = 0;

        // Add pairs for same x-coordinates
        for (int count : xCounts.values()) {
            totalPairs += (long)count * (count - 1) / 2;
        }

        // Add pairs for same y-coordinates
        for (int count : yCounts.values()) {
            totalPairs += (long)count * (count - 1) / 2;
        }

        // Subtract duplicate pairs for same (x, y)
        for (int count : pointCounts.values()) {
            totalPairs -= (long)count * (count - 1) / 2;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(totalPairs + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
