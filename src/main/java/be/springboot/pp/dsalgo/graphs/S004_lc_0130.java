package be.springboot.pp.dsalgo.graphs;

public class S004_lc_0130 {

    public void solve(char[][] board) {
        if (board == null
                || board.length == 0
                || board[0].length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        // Step 1: Mark all 'O's connected to the boundary as 'B'
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') markBoundaryConnected(board, i, 0);
            if (board[i][cols - 1] == 'O') markBoundaryConnected(board, i, cols - 1);
        }

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') markBoundaryConnected(board, 0, j);
            if (board[rows - 1][j] == 'O') markBoundaryConnected(board, rows - 1, j);
        }

        // Step 2: Transform the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X'; // Capture the region
                else if (board[i][j] == 'B') board[i][j] = 'O'; // Restore the boundary-connected region
            }
        }
    }

    private void markBoundaryConnected(char[][] board, int row, int col) { // Mark 'O' cells connected to the boundary using DFS
        if (row < 0
                || row >= board.length
                || col < 0
                || col >= board[0].length
                || board[row][col] != 'O') return;

        board[row][col] = 'B'; // Mark the cell as boundary-connected

        markBoundaryConnected(board, row - 1, col); // Up
        markBoundaryConnected(board, row + 1, col); // Down
        markBoundaryConnected(board, row, col - 1); // Left
        markBoundaryConnected(board, row, col + 1); // Right
    }
}
