package be.springboot.pp.dsalgo.maths;

import java.util.Arrays;

public class PrimeNumbers {

    public void sieveOfEratosthenes(int N) {
        // Step 1: Create a boolean array "isPrime[]" and initialize all entries as true
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true); // Assume all numbers are prime initially

        // Step 2: Mark 0 and 1 as non-prime
        isPrime[0] = false;
        isPrime[1] = false;

        // Step 3: Start marking non-prime numbers
        for (int p = 2; p * p <= N; p++)
            if (isPrime[p]) // If isPrime[p] is true, then mark all multiples of p as non-prime
                for (int multiple = p * p; multiple <= N; multiple += p)
                    isPrime[multiple] = false;

        // Step 4: Print all prime numbers
        for (int i = 2; i <= N; i++)
            if (isPrime[i]) System.out.print(i + " ");
    }

    public void power(String[] args) {
        int N = 50;  // Example input
        sieveOfEratosthenes(N);  // Output: 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47
    }
}
