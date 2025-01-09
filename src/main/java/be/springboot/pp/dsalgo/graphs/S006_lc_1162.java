package be.springboot.pp.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class S006_lc_1162 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public int maxDistance(int[][] grid) {
        int n = grid.length;

        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Add all land cells to the queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {  // Land cell
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // Edge Case: If no land or no water, return -1
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int maxDistance = -1;

        // Step 2: Perform BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // Check boundary and only process water cells
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                    grid[nx][ny] = grid[x][y] + 1; // Update distance
                    maxDistance = Math.max(maxDistance, grid[nx][ny] - 1); // Record max distance
                    queue.offer(new int[]{nx, ny}); // Add to queue
                }
            }
        }

        return maxDistance;
    }
}
