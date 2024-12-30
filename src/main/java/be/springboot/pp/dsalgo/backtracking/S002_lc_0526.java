package be.springboot.pp.dsalgo.backtracking;

public class S002_lc_0526 {

    public int countValidPermutations(int n) {
        boolean[] used = new boolean[n + 1]; // Array to track used numbers
        return backtrack(n, 1, used); // Start backtracking with count initialized to 0
    }

    private int backtrack(int n, int pos, boolean[] used) {
        // Base case: if we've filled all positions, this is a valid permutation
        if (pos > n) return 1;

        int count = 0;

        // Try to place each number at the current position
        for (int num = 1; num <= n; num++) {
            if (!used[num] && (num % pos == 0 || pos % num == 0)) {
                used[num] = true; // Mark the number as used and proceed
                count += backtrack(n, pos + 1, used);
                used[num] = false; // Unmark the number (backtrack)
            }
        }

        return count;
    }

    public static void main(String[] args) {
        S002_lc_0526 bp = new S002_lc_0526();
        System.out.println(bp.countValidPermutations(2));  // Expected output: 2
        System.out.println(bp.countValidPermutations(3));  // Expected output: 3
    }
}
