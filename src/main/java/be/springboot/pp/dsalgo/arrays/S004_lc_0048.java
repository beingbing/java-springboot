package be.springboot.pp.dsalgo.arrays;

public class S004_lc_0048 {

    private void swap(int[][] matrix, int row1, int col1, int row2, int col2) {
        int tmp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = tmp;
    }

    private void reverse(int[][] matrix, int row) {
        int n = matrix[row].length;
        int mid = n / 2;
        for (int i = 0; i < mid; i++) {
            swap(matrix, row, i, row, n - 1 - i);
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                swap(matrix, i, j, j, i);

        for (int i = 0; i < n; i++)
            reverse(matrix, i);
    }

}
