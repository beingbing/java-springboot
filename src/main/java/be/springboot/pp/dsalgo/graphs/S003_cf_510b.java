package be.springboot.pp.dsalgo.graphs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S003_cf_510b {
    static int[] dx = {1, 0, -1, 0}; // Direction vectors for rows (down, right, up, left)
    static int[] dy = {0, 1, 0, -1}; // Direction vectors for columns (down, right, up, left)
    static boolean[][] visited;
    static char[][] grid;
    static int n, m;

    public static boolean dfs(int x, int y, int px, int py, char color, int length) {
        visited[x][y] = true;

        for (int dir = 0; dir < 4; dir++) { // Explore all 4 neighbors
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (nx == px && ny == py) continue; // Skip the cell we just came from
            if (grid[nx][ny] == color) { // Check if the next cell has the same color
                if (visited[nx][ny]) { // If the neighbor is already visited and path length is 4 or more, a cycle exists
                    if (length >= 4) return true; // Cycle found
                } else if (dfs(nx, ny, x, y, color, length + 1)) return true;
            }
        }

        return false; // Backtrack: Mark the cell as unvisited for other paths
    }

    public static boolean containsCycle(char[][] gridInput) {
        n = gridInput.length;
        m = gridInput[0].length;
        grid = gridInput;
        visited = new boolean[n][m];

        // Check each cell as a starting point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) { // Start DFS from unvisited cells
                    if (dfs(i, j, -1, -1, grid[i][j], 1)) {
                        return true; // Cycle found
                    }
                }
            }
        }
        return false; // No cycle found
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) grid[i] = br.readLine().trim().toCharArray();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(containsCycle(grid) ? "Yes\n" : "No\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
