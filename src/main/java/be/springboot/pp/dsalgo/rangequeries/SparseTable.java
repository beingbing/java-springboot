package be.springboot.pp.dsalgo.rangequeries;

public class SparseTable {
    int[][] sparseTable;
    int[] log;

    public SparseTable(int[] input) {
        int n = input.length;
        int k = (int) (Math.log(n) / Math.log(2)) + 1;
        sparseTable = new int[n][k];
        log = new int[n + 1];

        // Precompute logs for fast lookup
        for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;

        // Build sparse table
        for (int i = 0; i < n; i++) sparseTable[i][0] = input[i];

        for (int j = 1; j < k; j++)
            for (int i = 0; i + (1 << j) <= n; i++)
                sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
    }

    int query(int l, int r) {
        int j = log[r - l + 1];
        return Math.min(sparseTable[l][j], sparseTable[r - (1 << j) + 1][j]);
    }
}
