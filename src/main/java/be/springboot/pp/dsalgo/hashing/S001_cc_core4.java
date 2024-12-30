package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;

public class S001_cc_core4 {
    public static int countTriangles(int n, int[][] points) {
        // Map to count occurrences of each x and y coordinate
        Map<Integer, Integer> xCount = new HashMap<>();
        Map<Integer, Integer> yCount = new HashMap<>();

        // Step 1: Populate the xCount and yCount maps
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            xCount.put(x, xCount.getOrDefault(x, 0) + 1);
            yCount.put(y, yCount.getOrDefault(y, 0) + 1);
        }

        // Step 2: Calculate the number of triangles
        long result = 0; // Use long to prevent overflow during calculations
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            // Number of pairs that can create a vertical line with current point
            int verticalPairs = xCount.get(x) - 1; // subtracting 1 to remove count of current point

            // Number of pairs that can create a horizontal line with current point
            int horizontalPairs = yCount.get(y) - 1;

            // number of triangles formed using current point as vertex
            result += (long) verticalPairs * horizontalPairs;
        }
        return (int) (result % 10000);
    }
}
