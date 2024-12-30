package be.springboot.pp.dsalgo.twopointers;

public class S001_gfg_minz_expr {
    public static void main(String[] args) {
        // Example input
        int[] A = {1, 4, 5, 8, 10};
        int[] B = {6, 9, 15};
        int[] C = {2, 3, 6, 6};

        System.out.println(minimizeDifference(A, B, C));
    }

    public static int minimizeDifference(int[] A, int[] B, int[] C) {
        int i = 0, j = 0, k = 0;
        int minDiff = Integer.MAX_VALUE;

        // Iterate while all pointers are within bounds
        while (i < A.length && j < B.length && k < C.length) {
            // Find current maximum and minimum values
            int maxVal = Math.max(A[i], Math.max(B[j], C[k]));
            int minVal = Math.min(A[i], Math.min(B[j], C[k]));

            // Update minimum difference
            minDiff = Math.min(minDiff, maxVal - minVal);

            // Move the pointer of the smallest value
            if (minVal == A[i]) i++;
            else if (minVal == B[j]) j++;
            else k++;
        }

        return minDiff;
    }
}
