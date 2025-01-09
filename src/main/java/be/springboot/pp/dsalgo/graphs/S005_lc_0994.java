package be.springboot.pp.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class S005_lc_0994 {
    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Directions for moving in the grid (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // Initialize the queue with all rotten oranges and count fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) queue.add(new int[]{i, j});
                else if (grid[i][j] == 1) freshCount++;
            }
        }

        // If no fresh oranges, return 0
        if (freshCount == 0) return 0;

        int minutes = 0;

        // Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0], y = current[1];

                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    // Check bounds and if the cell contains a fresh orange
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2; // Rot the orange
                        queue.add(new int[]{newX, newY});
                        freshCount--; // Decrease the count of fresh oranges
                        rottedThisMinute = true;
                    }
                }
            }

            // Increment time if any orange rotted this minute
            if (rottedThisMinute) minutes++;
        }

        // If there are still fresh oranges, return -1
        return freshCount == 0 ? minutes : -1;
    }
}
