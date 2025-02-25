package be.springboot.pp.dsalgo.maths;

import java.util.Scanner;

public class S002_cf_776b {
    static int[] spf = null;
    static int[] primeFactorCount = null;
    static int[] maxPrimeFactorTill = null;

    private static void processSpf(int n) {
        spf = new int[n + 1];
        primeFactorCount = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            spf[i] = i;
            primeFactorCount[i] = 1;
        }

        for (int p = 2; p * p <= n; p++)
            if (spf[p] == p)
                for (int multiple = p * p; multiple <= n; multiple += p)
                    if (spf[multiple] == multiple) {
                        spf[multiple] = p;
                        primeFactorCount[multiple]++;
                    }
    }

    private static void primeFactorPrefixMax(int n) {
        maxPrimeFactorTill = new int[n + 1];

        maxPrimeFactorTill[0] = 0;
        maxPrimeFactorTill[1] = 0;
        maxPrimeFactorTill[2] = 1;

        for (int i = 3; i <= n; i++)
            maxPrimeFactorTill[i] = Math.max(maxPrimeFactorTill[i - 1], primeFactorCount[i]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        processSpf(n + 2);
        primeFactorPrefixMax(n + 2);

        if (n <= 1) {
            System.out.println(1);
            System.out.println(1);
            return;
        }

//        System.out.println("spf: ");
//        for (int ele : spf) {
//            System.out.print(ele + " ");
//        }
//        System.out.println();

//        System.out.println("primeFactorCount: ");
//        for (int ele : primeFactorCount) {
//            System.out.print(ele + " ");
//        }
//        System.out.println();

//        System.out.println("maxPrimeFactorTill: ");
//        for (int ele : maxPrimeFactorTill) {
//            System.out.print(ele + " ");
//        }
//        System.out.println();

        System.out.println(maxPrimeFactorTill[n + 1]);
        for (int i = 2; i <= n + 1; i++) {
            System.out.print(primeFactorCount[i]);
            if (i != n+1) System.out.print(" ");
        }
        System.out.println();
    }
}
