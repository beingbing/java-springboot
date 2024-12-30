package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;

public class S001_lc_0149 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n; // If 2 or fewer points, they are trivially collinear.

        int maxPoints = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int overlap = 0, vertical = 0, localMax = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    // Overlapping points
                    overlap++;
                } else if (dx == 0) {
                    // Vertical line
                    vertical++;
                } else {
                    // Calculate reduced slope
                    int gcd = gcd(dy, dx);
                    dx /= gcd;
                    dy /= gcd;

                    // Maintain consistency for slope representation
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }

                    String slope = dy + "/" + dx; // Represent slope as a string
                    slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                    localMax = Math.max(localMax, slopeMap.get(slope));
                }
            }

            // Update maximum points on a line
            maxPoints = Math.max(maxPoints, Math.max(localMax, vertical) + overlap + 1);
        }

        return maxPoints;
    }

    // Helper to calculate GCD
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
