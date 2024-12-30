package be.springboot.pp.dsalgo.recursion;

public class S005_cf_676b {

    public static void main(String[] args) {
        int n = 3; // Pyramid height
        int t = 5; // Time in seconds
        System.out.println(countFullGlasses(n, t));
    }

    public static int countFullGlasses(int n, int t) {
        double[][] glasses = new double[n][n];
        glasses[0][0] = t; // Start pouring at the top glass

        // Distribute champagne across the glasses
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // Check if this glass has overflow
                if (glasses[i][j] > 1.0) {
                    double overflow = glasses[i][j] - 1.0;
                    glasses[i][j] = 1.0;

                    // Distribute half of overflow to the left and right glasses below
                    if (i + 1 < n) {
                        glasses[i + 1][j] += overflow / 2.0;
                        glasses[i + 1][j + 1] += overflow / 2.0;
                    }
                }
            }
        }

        // Count full glasses
        int fullGlasses = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                if (glasses[i][j] >= 1.0) fullGlasses++;

        return fullGlasses;
    }
}

class ChampagnePyramidRecursive {

    public static void main(String[] args) {
        int n = 3; // Height of the pyramid
        int t = 5; // Time in seconds
        System.out.println(countFullGlasses(n, t));
    }

    public static int countFullGlasses(int n, int t) {
        double[][] glasses = new double[n][n];
        pourChampagne(glasses, 0, 0, t); // Start pouring into the top glass

        // Count glasses that are completely full
        int fullGlasses = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                if (glasses[i][j] >= 1.0) fullGlasses++;

        return fullGlasses;
    }

    // Recursive function to pour champagne into each glass
    private static void pourChampagne(double[][] glasses, int row, int col, double amount) {
        // Base condition: if the row exceeds the height, stop pouring
        if (row >= glasses.length) return;

        // Add champagne to the current glass
        glasses[row][col] += amount;

        // If the glass overflows, distribute half to each glass in the next row
        if (glasses[row][col] > 1.0) {
            double overflow = glasses[row][col] - 1.0;
            glasses[row][col] = 1.0; // Limit this glass to a full capacity of 1.0
            pourChampagne(glasses, row + 1, col, overflow * 0.5);     // Left child glass
            pourChampagne(glasses, row + 1, col + 1, overflow * 0.5); // Right child glass
        }
    }
}


/**

 ### Optimal Solution in Java (Using Iteration for Efficiency)

 While recursion is conceptually elegant, iterative logic is often more efficient and straightforward here, given the need to manage flow in all glasses simultaneously. Thus, here is an efficient iterative solution in Java:

 ### Insights and Complexity

 - **Recursive Overflow Propagation**: The recursion efficiently spreads overflow champagne level-by-level.
 - **Time Complexity**: Approximately `O(2^n)` due to recursive calls expanding across the pyramid.
 - **Space Complexity**: `O(n^2)` to store the champagne in each glass.
 */