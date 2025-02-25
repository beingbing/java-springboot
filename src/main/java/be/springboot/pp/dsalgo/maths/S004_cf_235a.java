package be.springboot.pp.dsalgo.maths;

public class S004_cf_235a {

    public long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    public long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public long lcm(long a, long b, long c) {
        return lcm(a, lcm(b, c));
    }

    public long findMaxLCM(int N) {
        // If N is small, handle edge cases directly
        if (N <= 2) return N;
        if (N == 3) return 6;

        // Initialize the maximum LCM variable
        long maxLCM = 0;

        // Check the LCM of the following triplets:
        maxLCM = Math.max(maxLCM, lcm(N, N - 1, N - 2));
        maxLCM = Math.max(maxLCM, lcm(N, N - 1, N - 3));
        maxLCM = Math.max(maxLCM, lcm(N, N - 2, N - 3));
        maxLCM = Math.max(maxLCM, lcm(N - 1, N - 2, N - 3));

        // Return the maximum LCM found
        return maxLCM;
    }
}
