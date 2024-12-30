package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class S001_uva_00750 {
    // List to store all valid 92 configurations of 8-queens solutions.
    private static final List<int[]> validConfigurations = new ArrayList<>();

    public static void main(String[] args) {
        // Generate all valid 8-queens configurations once, to be reused for each test case.
        generateValidConfigurations(new int[8], 0);

        // Reading input for each test case
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;

        // Process each test case until no more input is available.
        while (scanner.hasNext()) {
            int[] currentConfig = new int[8];
            // Read the row positions of the queens in each of the 8 columns.
            for (int i = 0; i < 8; i++) {
                currentConfig[i] = scanner.nextInt() - 1; // Convert to 0-based index for easier handling.
            }

            // Find the minimum number of moves to convert currentConfig to a valid 8-queens solution.
            int minMoves = findMinimumMoves(currentConfig);

            // Print the result for the current test case with the case number.
            System.out.println("Case " + caseNumber + ": " + minMoves);
            caseNumber++;
        }

        // Close the scanner to release resources.
        scanner.close();
    }

    /**
     * Generates all valid 8-queens configurations by recursively placing queens on the board
     * ensuring no two queens attack each other in any direction. The generated configurations
     * are stored in the validConfigurations list.
     *
     * @param positions Array representing the row position of queens in each column.
     * @param column Current column to place a queen in.
     */
    private static void generateValidConfigurations(int[] positions, int column) {
        // If all 8 queens are placed successfully, add this configuration to the list.
        if (column == 8) {
            validConfigurations.add(positions.clone());
            return;
        }

        // Try placing a queen in each row of the current column.
        for (int row = 0; row < 8; row++) {
            // Check if it's safe to place a queen at (column, row).
            if (isSafe(positions, column, row)) {
                positions[column] = row; // Place the queen in this row for the current column.

                // Recur to place the queen in the next column.
                generateValidConfigurations(positions, column + 1);
            }
        }
    }

    /**
     * Checks if placing a queen at (column, row) is safe, meaning no other queens can attack it.
     *
     * @param positions Array holding current row positions of queens in prior columns.
     * @param column The column where the queen is to be placed.
     * @param row The row where the queen is to be placed.
     * @return True if it is safe to place the queen at (column, row), false otherwise.
     */
    private static boolean isSafe(int[] positions, int column, int row) {
        for (int col = 0; col < column; col++) {
            int otherRow = positions[col];
            // Check for queens in the same row or on either diagonal
            if (otherRow == row || Math.abs(otherRow - row) == Math.abs(col - column)) {
                return false; // Unsafe if another queen is in the same row or on a diagonal.
            }
        }
        return true; // Safe to place the queen here.
    }

    /**
     * Calculates the minimum number of moves required to make the current configuration match
     * any of the valid configurations stored in validConfigurations.
     *
     * @param currentConfig Array representing the row position of queens in the given configuration.
     * @return The minimum number of moves needed to make currentConfig a valid configuration.
     */
    private static int findMinimumMoves(int[] currentConfig) {
        int minMoves = Integer.MAX_VALUE;

        // Compare the current configuration with each valid 8-queens solution.
        for (int[] validConfig : validConfigurations) {
            int moves = 0;
            // Calculate the number of moves needed to change each column's queen to the correct row.
            for (int col = 0; col < 8; col++) {
                if (currentConfig[col] != validConfig[col]) {
                    moves++; // Increment moves for each column where the row position doesn't match.
                }
            }
            // Update minMoves if the current configuration requires fewer moves.
            minMoves = Math.min(minMoves, moves);
        }
        return minMoves;
    }
}
