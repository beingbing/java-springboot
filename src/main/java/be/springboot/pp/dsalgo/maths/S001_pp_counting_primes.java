package be.springboot.pp.dsalgo.maths;

import java.util.*;
import java.lang.*;
import java.io.*;

public class S001_pp_counting_primes {
    static int[] isPrime = null;
    static int[] primesCountTill = null;

    private static void processPrimesTill(int n) {
        isPrime = new int[n+1];
        Arrays.fill(isPrime, 1);

        isPrime[0] = 0;
        isPrime[1] = 0;

        for (int p = 2; p*p <= n; p++)
            if (isPrime[p] == 1)
                for (int multiple = p*p; multiple <= n; multiple += p)
                    isPrime[multiple] = 0;

        primesCountTill = new int[n+1];
        Arrays.fill(primesCountTill, 0);
        for (int i = 1; i <= n; i++)
            primesCountTill[i] = primesCountTill[i-1] + isPrime[i];
    }

    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] cases = new int[t];
        int maxNum = 0;

        for (int i = 0; i < t; i++) {
            cases[i] = sc.nextInt();
            maxNum = Math.max(maxNum, cases[i]);
        }

        processPrimesTill(maxNum);

        for (int i = 0; i < t; i++)
            System.out.println(primesCountTill[cases[i]]);
    }
}