package be.springboot.pp.dsalgo.recursion;

public class S003_cf_768b {
    public static void main(String[] args) {
        S003_cf_768b solver = new S003_cf_768b();
        System.out.println(solver.countOnesInRange(7, 2, 5)); // Expected output: 4
        System.out.println(solver.countOnesInRange(10, 3, 10)); // Expected output: 5
    }

    public long countOnesInRange(long n, long l, long r) {
        // Precompute the length of the binary structure for `n`
        long length = calculateTreeLength(n);
        // Recursively count the number of 1's within the range [l, r]
        return countOnes(n, l, r, 1, length);
    }

    // Recursive helper function
    private long countOnes(long n, long l, long r, long start, long length) {
        if (n == 0) return 0;   // Base case: `0` contributes no `1`s
        if (n == 1) return 1;   // Base case: `1` contributes one `1`

        long mid = start + length / 2; // Middle position
        long leftOnes = 0, rightOnes = 0;

        // Count in the left segment if range overlaps
        if (l <= mid - 1)
            leftOnes = countOnes(n / 2, l, Math.min(mid - 1, r), start, length / 2);

        // Middle element (only count if within the range)
        long midOnes = (l <= mid && mid <= r && n % 2 == 1) ? 1 : 0;

        // Count in the right segment if range overlaps
        if (r >= mid + 1)
            rightOnes = countOnes(n / 2, Math.max(mid + 1, l), r, mid + 1, length / 2);

        return leftOnes + midOnes + rightOnes;
    }

    // Calculate the length of the tree-like structure for a number `n`
    private long calculateTreeLength(long n) {
        if (n == 0 || n == 1) return 1;
        return 2 * calculateTreeLength(n / 2) + 1;
    }
}