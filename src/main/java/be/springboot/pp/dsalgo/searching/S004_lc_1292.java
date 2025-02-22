package be.springboot.pp.dsalgo.searching;

public class S004_lc_1292 {
    private static int[][] calPrefixSumMatrix(int[][] mat, int m, int n) {
        int[][] prefixSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = mat[i - 1][j - 1]
                        + prefixSum[i - 1][j]
                        + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1];
            }
        }
        return prefixSum;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] prefixSum = calPrefixSumMatrix(mat, m, n); // Step 1: Build the prefix sum matrix

        int left = 1, right = Math.min(m, n);

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canFormSquareWithSide(mid, prefixSum, threshold)) left = mid + 1;
            else right = mid - 1; // Try smaller side lengths
        }

        return right;
    }

    private static boolean canFormSquareWithSide(int side, int[][] prefixSum, int threshold) {
        int m = prefixSum.length - 1;
        int n = prefixSum[0].length - 1;

        for (int i = side; i <= m; i++) {
            for (int j = side; j <= n; j++) {
                int sum = prefixSum[i][j]
                        - prefixSum[i - side][j]
                        - prefixSum[i][j - side]
                        + prefixSum[i - side][j - side];
                if (sum <= threshold) return true;
            }
        }

        return false;
    }
}
