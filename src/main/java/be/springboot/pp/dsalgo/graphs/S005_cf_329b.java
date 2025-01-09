package be.springboot.pp.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S005_cf_329b {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read dimensions of the grid
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        char[][] forest = new char[rows][cols];
        int[][] distances = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // Directions for BFS traversal (right, left, down, up)
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        // Read the forest grid and locate the exit
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < cols; j++) {
                forest[i][j] = line.charAt(j);
                distances[i][j] = Integer.MAX_VALUE; // Initialize distances to a large value

                if (forest[i][j] == 'E') {
                    // Add exit to BFS queue and mark as visited
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    distances[i][j] = 0; // Distance to itself is 0
                }
            }
        }

        int minDistanceToStart = Integer.MAX_VALUE; // Minimum distance from the start to the exit
        int maxBreederCount = 0; // Maximum count of breeders intercepted

        // Perform BFS from the exit
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            for (int[] direction : directions) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];

                // Check bounds and validity
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                        !visited[newRow][newCol] && forest[newRow][newCol] != 'T') {

                    visited[newRow][newCol] = true;
                    distances[newRow][newCol] = distances[currentRow][currentCol] + 1; // Update distance
                    queue.add(new int[]{newRow, newCol}); // Add to queue for further traversal

                    // Check if this is the start cell
                    if (forest[newRow][newCol] == 'S') {
                        minDistanceToStart = distances[newRow][newCol];
                    }
                }
            }
        }

        // Traverse the grid again to count breeders within the path
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Character.isDigit(forest[i][j])) {
                    int breederCount = forest[i][j] - '0'; // Number of breeders at this cell
                    if (distances[i][j] <= minDistanceToStart) { // Within reach
                        maxBreederCount += breederCount;
                    }
                }
            }
        }

        // Output the result
        System.out.println(maxBreederCount);
    }
}
