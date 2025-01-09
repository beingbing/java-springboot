package be.springboot.pp.dsalgo.graphs;

public class S006_lc_0529 {

    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // N, S, W, E
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // NW, NE, SW, SE
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int rows = board.length;
        int cols = board[0].length;
        int clickRow = click[0];
        int clickCol = click[1];

        if (board[clickRow][clickCol] == 'M') { // If the clicked cell is a mine, reveal it as 'X'
            board[clickRow][clickCol] = 'X';
            return board;
        }

        dfs(board, clickRow, clickCol, rows, cols);
        return board;
    }

    private void dfs(char[][] board, int row, int col, int rows, int cols) {
        if (!isValidCell(row, col, rows, cols) || board[row][col] != 'E') return;

        int adjacentMines = countAdjacentMines(board, row, col, rows, cols);

        if (adjacentMines > 0) board[row][col] = (char) (adjacentMines + '0'); // If there are adjacent mines, reveal as a digit
        else { // If no adjacent mines, reveal as 'B' and recurse
            board[row][col] = 'B';
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                dfs(board, newRow, newCol, rows, cols);
            }
        }
    }

    private int countAdjacentMines(char[][] board, int row, int col, int rows, int cols) {
        int mineCount = 0;
        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (isValidCell(newRow, newCol, rows, cols) && board[newRow][newCol] == 'M') mineCount++;
        }
        return mineCount;
    }

    private boolean isValidCell(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
