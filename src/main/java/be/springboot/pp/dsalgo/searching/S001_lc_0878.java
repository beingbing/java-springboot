package be.springboot.pp.dsalgo.searching;

public class S001_lc_0878 {
    private final int MOD = 1_000_000_007;

    // Helper function to find the Greatest Common Divisor (GCD)
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper function to find the Least Common Multiple (LCM)
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // Function to find the N-th magical number
    public long nthMagicalNumber(int n, int a, int b) {
        long left = 1;
        long right = (long) n * Math.min(a, b);
        long lcmAB = lcm(a, b);

        while (left < right) {
            long mid = left + (right - left) / 2;
            long count = mid / a + mid / b - mid / lcmAB;

            if (count < n) left = mid + 1;
            else right = mid;
        }

        return left % MOD;
    }
}