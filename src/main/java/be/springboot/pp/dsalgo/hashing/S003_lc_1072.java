package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;

public class S003_lc_1072 {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> patternFrequency = new HashMap<>(); // Map to store the frequency of normalized row patterns

        for (int[] row : matrix) {
            StringBuilder normalizedPattern = new StringBuilder(); // Generate a normalized pattern based on the first element of the row
            int referenceValue = row[0];

            // Convert the row into a "canonical" binary pattern
            for (int cell : row)
                if (cell == referenceValue) normalizedPattern.append('1'); // Same as the first element
                else normalizedPattern.append('0'); // Different from the first element

            // Increment the frequency of this pattern in the map
            String patternString = normalizedPattern.toString();
            patternFrequency.put(patternString, patternFrequency.getOrDefault(patternString, 0) + 1);
        }

        // Find the maximum frequency of any pattern
        int maxEqualRows = 0;
        for (int frequency : patternFrequency.values()) maxEqualRows = Math.max(maxEqualRows, frequency);

        return maxEqualRows;
    }

    public static void main(String[] args) {
        S003_lc_1072 solution = new S003_lc_1072();
        int[][] matrix = {
                {0, 1, 0},
                {1, 0, 1},
                {1, 0, 1},
                {0, 1, 0}
        };
        System.out.println(solution.maxEqualRowsAfterFlips(matrix)); // Output: 2
    }
}
