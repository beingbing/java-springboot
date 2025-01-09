package be.springboot.pp.dsalgo.graphs;

public class S003_lc_0200 {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rowCount = grid.length;       // Number of rows
        int colCount = grid[0].length;    // Number of columns
        int islandCount = 0;              // Counter for number of islands

        boolean[][] visited = new boolean[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) { // If the cell is '1' and not visited, it's a new island
                    islandCount++; // Increment island count
                    exploreIsland(grid, visited, row, col); // DFS to explore entire island
                }
            }
        }

        return islandCount; // Return the final count of islands
    }

    private void exploreIsland(char[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0
                || col < 0
                || row >= grid.length
                || col >= grid[0].length
                || grid[row][col] == '0'
                || visited[row][col]) return; // Base case: Stop exploration

        visited[row][col] = true;

        exploreIsland(grid, visited, row - 1, col); // Up
        exploreIsland(grid, visited, row + 1, col); // Down
        exploreIsland(grid, visited, row, col - 1); // Left
        exploreIsland(grid, visited, row, col + 1); // Right
    }
}
