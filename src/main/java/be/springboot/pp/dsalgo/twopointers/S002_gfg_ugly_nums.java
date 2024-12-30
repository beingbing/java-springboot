package be.springboot.pp.dsalgo.twopointers;

public class S002_gfg_ugly_nums {
    public static int nthUglyNumber(int n) {
        // Array to store the first n ugly numbers
        int[] dp = new int[n];
        dp[0] = 1; // The first ugly number is 1

        // Pointers for multiples of 2, 3, and 5
        int p2 = 0, p3 = 0, p5 = 0;

        // Generate the sequence of ugly numbers
        for (int i = 1; i < n; i++) {
            // Calculate next multiples of 2, 3, and 5
            int nextMultipleOf2 = dp[p2] * 2;
            int nextMultipleOf3 = dp[p3] * 3;
            int nextMultipleOf5 = dp[p5] * 5;

            // The next ugly number is the minimum of these
            int nextUgly = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
            dp[i] = nextUgly;

            // Move the pointers for the chosen factors
            // Ensures no duplicate numbers by moving the pointer only when its value matches the chosen ugly number.
            if (nextUgly == nextMultipleOf2) p2++;
            if (nextUgly == nextMultipleOf3) p3++;
            if (nextUgly == nextMultipleOf5) p5++;
        }

        // Return the nth ugly number
        return dp[n - 1];
    }
}
