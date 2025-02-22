package be.springboot.pp.dsalgo.searching;

public class S003_lc_0074 {
    public boolean searchMatrix(int[][] mat, int key) {
        int n = mat.length;
        if (n == 0) return false;
        int m = mat[0].length;

        int left = 0, right = n * m - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int row = mid / m;
            int col = mid % m;

            int midEle = mat[row][col];

            if (midEle > key) right = mid - 1;
            else if (midEle < key) left = mid + 1;
            else return true;
        }

        return false;
    }
}
