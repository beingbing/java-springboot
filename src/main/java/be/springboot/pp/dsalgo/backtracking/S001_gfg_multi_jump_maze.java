package be.springboot.pp.dsalgo.backtracking;

public class S001_gfg_multi_jump_maze {
    private int n;
    private int[][] ans;

    public int[][] ShortestDistance(int[][] matrix) {
        n = matrix.length;
        ans = new int[n][n];

        // edge cases
        if (n == 0 || (matrix[0][0] == 0 && n != 1)) return new int[][]{{-1}};

        matrix[n - 1][n - 1] = 1;  //marking destination as un-blocked

        if (backtrack(matrix, 0, 0)) return ans;
        else return new int[][]{{-1}};
    }

    private boolean backtrack(int[][] matrix, int i, int j) {
        if (!isSafe(matrix, i, j)) return false;

        if (i == n - 1 && j == n - 1) {
            ans[i][j] = 1;
            return true;
        }

        ans[i][j] = 1;
        int jump = matrix[i][j];

        for (int hop = 1; hop <= jump; hop++) { // using loop to execute all jumps
            if (backtrack(matrix, i, j + hop)) return true; // right
            if (backtrack(matrix, i + hop, j)) return true; // down
        }
        ans[i][j] = 0;
        return false;
    }

    private boolean isSafe(int[][] mat, int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n && mat[i][j] != 0;
    }
}