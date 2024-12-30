package be.springboot.pp.dsalgo.maths;

import java.util.ArrayList;
import java.util.Arrays;

public class LargeRangePrimeFinder {

    public static ArrayList<Integer> sieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        ArrayList<Integer> primes = new ArrayList<>();

        isPrime[0] = isPrime[1] = false; // 0 and 1 are not primes
        for (int p = 2; p * p <= limit; p++)
            if (isPrime[p]) {
                primes.add(p);
                for (int multiple = p * p; multiple <= limit; multiple += p)
                    isPrime[multiple] = false;
            }
        return primes;
    }

    public static ArrayList<Long> segmentedSieve(long l, long r) {
        int limit = (int) Math.sqrt(r); // Step 1: calculate sqrt of r
        ArrayList<Integer> primes = sieve(limit); // step 2: find primes till sqrt of r

        // step 3: create a pseudo array to represent each number in given range as a potential prime.
        boolean[] isPrime = new boolean[(int) (r - l + 1)];
        Arrays.fill(isPrime, true);

        // step 4: Marking off multiples of each prime in the range [l, r]
        for (int prime : primes) {
            long start = Math.max((long) prime * prime, (l + prime - 1) / prime * prime);
            for (long multiple = start; multiple <= r; multiple += prime) {
                isPrime[(int) (multiple - l)] = false;
            }
        }

        // step 5: Collect all primes from the range [l, r]
        ArrayList<Long> result = new ArrayList<>();
        for (int i = 0; i <= r - l; i++) if (isPrime[i]) result.add(l + i);
        return result;
    }

    public static void main(String[] args) {
        long l = 10000000000L;
        long r = 10000000500L;

        ArrayList<Long> primesInRange = segmentedSieve(l, r);
        System.out.println("Primes in the range [" + l + ", " + r + "]: " + primesInRange);
    }
}
