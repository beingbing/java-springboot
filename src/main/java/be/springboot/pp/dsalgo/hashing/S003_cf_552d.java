package be.springboot.pp.dsalgo.hashing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class S003_cf_552d {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        if (n < 3) {
            bw.write(0 + "\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().trim().split("\\s+");
            points[i][0] = Integer.parseInt(input[0]);
            points[i][1] = Integer.parseInt(input[1]);
        }

        long totalTriangles = (long) n * (n - 1) * (n - 2) / 6; // Total number of triangles

//        long collinearTriangles = 0;
//
//        // Check all combinations of three points
//        for (int i = 0; i < n; i++)
//            for (int j = i + 1; j < n; j++)
//                for (int k = j + 1; k < n; k++)
//                    if (isCollinear(points[i], points[j], points[k])) collinearTriangles++;
//
//        long validTriangles = totalTriangles - collinearTriangles;

        // Count collinear triplets
        long collinearCount = getCollinearPointsCount(n, points);

        long validTriangles = totalTriangles - collinearCount;

        bw.write(validTriangles + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static long getCollinearPointsCount(int n, int[][] points) {
        long collinearCount = 0;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            for (int j = i+1; j < n; j++) {
                // Calculate slope as a fraction (dy, dx)
                int dy = points[j][1] - points[i][1];
                int dx = points[j][0] - points[i][0];
                int gcd = gcd(dy, dx);
                dy /= gcd;
                dx /= gcd;

                // Normalize the slope to avoid duplicates
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dy = -dy;
                    dx = -dx;
                }

                // Use the normalized slope as a key
                String slope = dy + "/" + dx;
                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
            }

            // For each unique slope, calculate the number of collinear triplets
            for (int count : slopeCount.values()) {
                if (count >= 2) {
                    collinearCount += (long) count * (count - 1) / 2;
                }
            }
        }
        return collinearCount;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static boolean isCollinear(int[] p1, int[] p2, int[] p3) {
        int x1 = p1[0], y1 = p1[1];
        int x2 = p2[0], y2 = p2[1];
        int x3 = p3[0], y3 = p3[1];

        return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) == 0; // Determinant formula for collinearity
    }
}
