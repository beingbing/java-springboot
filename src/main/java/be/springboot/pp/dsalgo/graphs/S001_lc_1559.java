package be.springboot.pp.dsalgo.graphs;

public class S001_lc_1559 {
    private final int[] dx = {0, 0, 1, -1}; // Direction vectors for row movement
    private final int[] dy = {1, -1, 0, 0}; // Direction vectors for column movement
    private boolean[][] visited;
    private int m, n; // Grid dimensions

    public boolean containsCycle(char[][] grid) {
        m = grid.length;  // Rows
        n = grid[0].length; // Columns
        visited = new boolean[m][n];

        // Iterate through each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && dfs(grid, i, j, -1, -1, grid[i][j])) {
                    // Start DFS only if the cell is unvisited and Pass parent as (-1, -1)
                    return true; // Cycle found
                }
            }
        }
        return false; // No cycle found
    }

    private boolean dfs(char[][] grid, int x, int y, int parentX, int parentY, char target) {
        // Mark current cell as visited
        visited[x][y] = true;

        // Explore all 4 directions
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // Next row
            int ny = y + dy[i]; // Next column

            // Check if next cell is valid
            if (nx >= 0 && ny >= 0 && nx < m && ny < n && grid[nx][ny] == target) {
                if (nx == parentX && ny == parentY) continue; // Skip parent cell
                if (visited[nx][ny]) return true; // Cycle detected
                if (dfs(grid, nx, ny, x, y, target)) return true; // Recursive DFS call
            }
        }
        return false; // No cycle found in this path
    }
}
