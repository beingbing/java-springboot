package be.springboot.pp.dsalgo.maths;

import java.util.Arrays;
import java.util.Scanner;

public class S004_pp_t_prime {
    private static final int MAX = 1_000_000;
    private static boolean[] isPrime = new boolean[MAX + 1];

    private static void processPrimes(int n) {
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int multiple = p * p; multiple <= n; multiple += p) {
                    isPrime[multiple] = false;
                }
            }
        }
    }

    private static boolean isTPrime(long n) {
        long val = (long) Math.sqrt(n);
        // Check if n is a perfect square and its square root is a prime number
        return val * val == n && isPrime[(int) val];
    }

    public static void main(String[] args) {
        processPrimes(MAX);

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        while (n-- > 0) {
            long ele = sc.nextLong();

            if (isTPrime(ele)) System.out.println("YES");
            else System.out.println("NO");
        }

        sc.close();
    }
}
