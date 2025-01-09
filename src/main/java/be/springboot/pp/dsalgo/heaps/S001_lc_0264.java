package be.springboot.pp.dsalgo.heaps;

public class S001_lc_0264 {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1; // First ugly number is 1
        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            // Compute the next multiples
            int next2 = ugly[p2] * 2;
            int next3 = ugly[p3] * 3;
            int next5 = ugly[p5] * 5;

            // Select the smallest number
            int nextUgly = Math.min(next2, Math.min(next3, next5));
            ugly[i] = nextUgly;

            // Increment pointers for the used factors
            if (nextUgly == next2) p2++;
            if (nextUgly == next3) p3++;
            if (nextUgly == next5) p5++;
        }

        // Step 3: Return the nth ugly number
        return ugly[n - 1];
    }
}
