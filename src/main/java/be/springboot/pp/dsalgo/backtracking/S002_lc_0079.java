package be.springboot.pp.dsalgo.backtracking;

public class S002_lc_0079 {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (dfs(board, word, i, j, 0))
                    return true;

        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int curCharIndex) {
        // Base case: if we reach the end of the word, we've found a match
        if (curCharIndex == word.length()) return true;

        // Boundary check and character match check
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(curCharIndex))
            return false;

        // Mark the cell as visited by changing it to a temporary character
        char holdRealVal = board[row][col];
        board[row][col] = '#';

        // Explore all four directions: up, down, left, right
        boolean found = dfs(board, word, row + 1, col, curCharIndex + 1)
                || dfs(board, word, row - 1, col, curCharIndex + 1)
                || dfs(board, word, row, col + 1, curCharIndex + 1)
                || dfs(board, word, row, col - 1, curCharIndex + 1);

        // Restore the original character after exploring
        board[row][col] = holdRealVal;

        return found;
    }
}
