package be.springboot.pp.dsalgo.recursion;

public class S001_lc_0050 {

    // Recursive approach for n^k with Exponentiation by Squaring
    public static double powerRecursive(double n, int k) {
        if (k == 0) return 1;

        // Handle negative exponent
        if (k < 0) {
            n = 1 / n;
            k = -k;
        }

        double halfPower = powerRecursive(n, k / 2);
        if (k % 2 == 0) return halfPower * halfPower;
        else return n * halfPower * halfPower;
    }

    // Iterative approach for n^k with Exponentiation by Squaring
    public static double powerIterative(double n, int k) {
        if (k == 0) return 1;

        // Handle negative exponent
        double result = 1.0;
        long exponent = k; // Use long to handle large negative k
        if (exponent < 0) {
            n = 1 / n;
            exponent = -exponent;
        }

        // Loop to apply Exponentiation by Squaring iteratively
        while (exponent > 0) {
            if (exponent % 2 == 1) result *= n;
            n *= n;
            exponent /= 2;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(powerRecursive(2, 10));  // Output: 1024.0
        System.out.println(powerIterative(2, -3));  // Output: 0.125
    }
}
