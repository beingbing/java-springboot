package be.springboot.pp.dsalgo.backtracking;

class S001_lc_0980 {
    int rows, cols, totalPaths;

    public int uniquePathsIII(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        int startX = 0, startY = 0, emptyCells = rows * cols;

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (grid[r][c] == -1) emptyCells--;
                else if (grid[r][c] == 1) {
                    startX = r;
                    startY = c;
                }

        backtrack(grid, startX, startY, emptyCells);
        return totalPaths;
    }

    private void backtrack(int[][] grid, int row, int col, int remainingCells) {
        if (grid[row][col] == 2 && remainingCells == 1) {
            totalPaths++;
            return;
        }

        int holdRealVal = grid[row][col];
        grid[row][col] = -1; // temporarily marking cell as visited
        remainingCells--;

        if (isSafe(grid, row - 1, col)) backtrack(grid, row - 1, col, remainingCells);
        if (isSafe(grid, row, col + 1)) backtrack(grid, row, col + 1, remainingCells);
        if (isSafe(grid, row + 1, col)) backtrack(grid, row + 1, col, remainingCells);
        if (isSafe(grid, row, col - 1)) backtrack(grid, row, col - 1, remainingCells);

        grid[row][col] = holdRealVal;
    }

    private boolean isSafe(int[][] grid, int row, int col) {
        return row >= 0
                && row < rows
                && col >= 0
                && col < cols
                && (grid[row][col] == 0 || grid[row][col] == 2);
    }
}