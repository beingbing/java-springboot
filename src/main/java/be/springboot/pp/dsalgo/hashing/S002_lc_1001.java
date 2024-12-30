package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class S002_lc_1001 {
    String getKey(int row, int col) {
        return row + "," + col;
    }

    boolean isValid( int i,  int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        // Hash maps to track row, column, and diagonal illumination
        Map<Integer, Integer> rowCount = new HashMap<>();
        Map<Integer, Integer> colCount = new HashMap<>();
        Map<Integer, Integer> diagCount = new HashMap<>();
        Map<Integer, Integer> antiDiagCount = new HashMap<>();

        // Set to store active lamp positions
        Set<String> activeLamps = new HashSet<>();

        // Initialize lamps
        for (int[] lamp : lamps) {
            int row = lamp[0], col = lamp[1];
            String key = getKey(row, col);

            // Skip duplicates
            if (activeLamps.contains(key)) continue;

            activeLamps.add(key);
            rowCount.put(row, rowCount.getOrDefault(row, 0) + 1);
            colCount.put(col, colCount.getOrDefault(col, 0) + 1);
            diagCount.put(row - col, diagCount.getOrDefault(row - col, 0) + 1);
            antiDiagCount.put(row + col, antiDiagCount.getOrDefault(row + col, 0) + 1);
        }

        // Direction vectors for turning off adjacent lamps
        int[][] directions = {{-1, 0}, {-1, 1}, {-1, -1}, {0, 0}, {0, 1}, {0, -1}, {1, 0}, {1, 1}, {1, -1}};

        // Array to store the results
        int[] result = new int[queries.length];

        // Process queries
        for (int i = 0; i < queries.length; i++) {
            int row = queries[i][0], col = queries[i][1];

            // Check if the cell is illuminated
            if (rowCount.getOrDefault(row, 0) > 0 ||
                    colCount.getOrDefault(col, 0) > 0 ||
                    diagCount.getOrDefault(row - col, 0) > 0 ||
                    antiDiagCount.getOrDefault(row + col, 0) > 0) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

            // Turn off lamps in the queried cell and its neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0], newCol = col + dir[1];
                if (!isValid(newRow, newCol, n)) continue;

                String key = getKey(newRow, newCol);
                if (!activeLamps.contains(key)) continue;

                activeLamps.remove(key);
                rowCount.put(newRow, rowCount.get(newRow) - 1);
                colCount.put(newCol, colCount.get(newCol) - 1);
                diagCount.put(newRow - newCol, diagCount.get(newRow - newCol) - 1);
                antiDiagCount.put(newRow + newCol, antiDiagCount.get(newRow + newCol) - 1);

                // Remove empty counts from maps
                if (rowCount.get(newRow) == 0) rowCount.remove(newRow);
                if (colCount.get(newCol) == 0) colCount.remove(newCol);
                if (diagCount.get(newRow - newCol) == 0) diagCount.remove(newRow - newCol);
                if (antiDiagCount.get(newRow + newCol) == 0) antiDiagCount.remove(newRow + newCol);
            }
        }

        return result;
    }
}
