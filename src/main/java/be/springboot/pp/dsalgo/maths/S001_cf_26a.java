package be.springboot.pp.dsalgo.maths;

import java.util.Scanner;

public class S001_cf_26a {
    static int MAX = 1000000;
    static int[] spf = null;
    static int[] primeDivisorCount = null;
    static int[] happyNumbersTill = null;

    private static void processSpfTill(int n) {
        spf = new int[n+1];

        for (int i = 0; i <= n; i++) spf[i] = i;

        for (int p = 2; p * p <= n; p++)
            if (spf[p] == p)
                for (int multiple = p * p; multiple <= n; multiple += p)
                    if (spf[multiple] == multiple) spf[multiple] = p;
    }

    private static int printDivisorsCount(int x) {
        int divisorsCount = 0;
        while (x > 1) {
            int prime = spf[x];
            while (x % prime == 0) x /= prime;
            divisorsCount++;
        }
        return divisorsCount;
    }

    private static void processPrimeDivisors(int n) {
        primeDivisorCount = new int[n+1];

        for (int i = 0; i <= n; i++)
            primeDivisorCount[i] = printDivisorsCount(i);
    }

    private static void processHappyNumbers(int n) {
        happyNumbersTill = new int[n+1];

        happyNumbersTill[0] = 0;

        for (int i = 1; i <= n; i++) {
            happyNumbersTill[i] = happyNumbersTill[i-1] + (primeDivisorCount[i] == 2 ? 1 : 0);
        }
    }

    public static void main (String[] args) throws Exception {
        processSpfTill(MAX);
        processPrimeDivisors(MAX);
        processHappyNumbers(MAX);
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt(), divisorsCount, numsCount = 0;

            System.out.println(happyNumbersTill[n]);
        }
        sc.close();
    }
}
