package be.springboot.pp.dsalgo.searching;

public class S003_lc_0668 {

    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (countLessEqual(mid, m, n) < k) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    // Function to count numbers <= x in the m x n multiplication table
    private int countLessEqual(int x, int m, int n) {
        int count = 0;

        // For each row, count numbers that are <= x
        for (int i = 1; i <= m; i++)
            count += Math.min(x / i, n); // each row has numbers i, 2i, 3i, ..., up to n*i

        return count;
    }
}