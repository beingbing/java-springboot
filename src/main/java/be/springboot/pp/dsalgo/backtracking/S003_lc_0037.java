package be.springboot.pp.dsalgo.backtracking;

public class S003_lc_0037 {
    private boolean[][] rows = new boolean[9][9];
    private boolean[][] cols = new boolean[9][9];
    private boolean[][] boxes = new boolean[9][9];

    public void solveSudoku(char[][] board) {
        // Initialize the helper arrays based on the initial board
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (board[r][c] != '.') {
                    int num = board[r][c] - '1';
                    rows[r][num] = true;
                    cols[c][num] = true;
                    boxes[getBoxIndex(r, c)][num] = true;
                }

        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int row, int col) {
        // Move to next row if column is out of bounds
        if (col == 9) {
            col = 0;
            row++;
        }

        // If we reach the end of the board, the puzzle is solved
        if (row == 9) return true;

        // If cell is already filled, move to the next cell
        if (board[row][col] != '.') return backtrack(board, row, col + 1);

        for (int num = 0; num < 9; num++) {
            int boxIndex = getBoxIndex(row, col);

            // Check if the number can be placed in row, column, and box
            if (!rows[row][num] && !cols[col][num] && !boxes[boxIndex][num]) {
                // Place the number and mark the constraints
                board[row][col] = (char) (num + '1');
                rows[row][num] = cols[col][num] = boxes[boxIndex][num] = true;

                // Recursively attempt to fill the rest of the board
                if (backtrack(board, row, col + 1)) return true;  // If successful, stop and return true

                // Backtrack by removing the number and unmarking the constraints
                board[row][col] = '.';
                rows[row][num] = cols[col][num] = boxes[boxIndex][num] = false;
            }
        }
        return false;  // Trigger backtracking if no valid number is found
    }

    private int getBoxIndex(int row, int col) {
        return (row / 3) * 3 + (col / 3); // Calculate index of the 3x3 sub-box
    }

    public static void main(String[] args) {
        S003_lc_0037 solver = new S003_lc_0037();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        solver.solveSudoku(board);

        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}