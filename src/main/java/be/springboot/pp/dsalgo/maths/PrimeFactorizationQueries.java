package be.springboot.pp.dsalgo.maths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrimeFactorizationQueries {

    public static int[] preprocessSmallestPrimeFactors(int n) {
        int[] spf = new int[n + 1]; // spf[i] will store the smallest prime factor of i

        // Initialize spf[i] to i (because every number is its own smallest prime factor initially)
        for (int i = 1; i <= n; i++) spf[i] = i;

        // Use a modified Sieve of Eratosthenes to compute the smallest prime factors
        for (int i = 2; i * i <= n; i++)
            if (spf[i] == i) // If i is prime (i.e., spf[i] == i), then mark its multiples
                for (int j = i * i; j <= n; j += i)
                    if (spf[j] == j) spf[j] = i; // Mark the smallest prime factor for j as i, if not already marked

        return spf;
    }

    public static void printPrimeFactors(int x, int[] spf) {
        List<Integer> factors = new ArrayList<>();
        while (x != 1) {
            factors.add(spf[x]);
            x /= spf[x];  // Keep dividing x by its smallest prime factor
        }
        System.out.println(factors);
    }

    public static void printDivisorsCount(int x, int[] spf) {
        int divisorCount = 1;
        while (x != 1) {
            int prime = spf[x];
            int count = 0;
            while (x % prime == 0) {  // Count occurrences of prime in the factorization of n
                x /= prime;
                count++;
            }
            divisorCount *= (count + 1);  // Apply the formula
        }

        System.out.println("divisor count: " + divisorCount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the maximum number n
        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();

        // Preprocess smallest prime factors for all numbers up to n
        int[] spf = preprocessSmallestPrimeFactors(n);

        // Input the number of queries
        System.out.print("Enter the number of queries: ");
        int Q = sc.nextInt();

        // Handle each query
        System.out.println("Enter the query numbers:");
        for (int i = 0; i < Q; i++) {
            int query = sc.nextInt();
            System.out.print("Prime factors of " + query + ": ");
            printPrimeFactors(query, spf);
            printDivisorsCount(query, spf);
        }
    }
}
