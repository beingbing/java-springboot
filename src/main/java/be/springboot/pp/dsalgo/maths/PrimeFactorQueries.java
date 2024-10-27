package be.springboot.pp.dsalgo.maths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeFactorQueries {

    // Function to preprocess prime factors for all numbers up to n
    public static List<Integer>[] preprocessPrimeFactors(int n) {
        // Create an array of lists to store prime factors for each number
        List<Integer>[] primeFactors = new ArrayList[n + 1];

        // Initialize each list
        for (int i = 0; i <= n; i++) {
            primeFactors[i] = new ArrayList<>();
        }

        // Use a modified Sieve of Eratosthenes to populate prime factors
        for (int i = 2; i <= n; i++) {
            // If i is a prime number (i.e., its primeFactors list is empty)
            if (primeFactors[i].isEmpty()) {
                // Mark all multiples of i with i as a prime factor
                for (int multiple = i; multiple <= n; multiple += i) {
                    primeFactors[multiple].add(i);
                }
            }
        }

        return primeFactors;
    }

    // Function to handle multiple queries
    public static void handleQueries(int[] queries, List<Integer>[] primeFactors) {
        for (int query : queries) {
            System.out.println("Prime factors of " + query + ": " + primeFactors[query]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the maximum number n
        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();

        // Preprocess prime factors for all numbers up to n
        List<Integer>[] primeFactors = preprocessPrimeFactors(n);

        for (var prime : primeFactors) {
            System.out.println("prime factors are: " + prime);
        }

        // Input the number of queries
        System.out.print("Enter the number of queries: ");
        int Q = sc.nextInt();

        // Handle each query
        int[] queries = new int[Q];
        System.out.println("Enter the query numbers:");
        for (int i = 0; i < Q; i++) {
            queries[i] = sc.nextInt();
        }

        // Output the prime factors for each query
        handleQueries(queries, primeFactors);
    }
}
