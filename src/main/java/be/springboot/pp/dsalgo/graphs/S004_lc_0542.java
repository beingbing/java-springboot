package be.springboot.pp.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class S004_lc_0542 {
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Directions for moving in the grid (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] dist = new int[m][n];

        // Initialize distances and queue
        Queue<int[]> queue = new LinkedList<>();

        // Populate the queue with all '0' cells and set distances for '1' to Integer.MAX_VALUE
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    queue.add(new int[]{i, j});
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Perform BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // Check bounds and if a shorter distance can be found
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (dist[newX][newY] > dist[x][y] + 1) {
                        dist[newX][newY] = dist[x][y] + 1;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }

        return dist;
    }
}
