package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimePermutation {
    private static final int MAX = 100;
    private static final int MOD = 1_000_000_007;
    static boolean[] isPrime = new boolean[MAX + 1];
    static long[] factorial = new long[MAX + 1];
    static int[] primeCount = new int[MAX + 1];

    static {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p <= MAX; p++)
            if (isPrime[p])
                for (int m = p * p; m <= MAX; m += p)
                    isPrime[m] = false;

        factorial[0] = 1;
        for (int i = 1; i <= MAX; i++)
            factorial[i] = (factorial[i - 1] * i) % MOD;

        primeCount[0] = primeCount[1] = 0;
        for (int i = 1; i <= MAX; i++) {
            if (!isPrime[i]) primeCount[i] = primeCount[i - 1];
            else primeCount[i] = primeCount[i - 1] + 1;
        }
    }

    public static long countValidPermutations(int n) {
        int nonPrimeCount = n - primeCount[n];
        return (factorial[primeCount[n]] * factorial[nonPrimeCount]) % MOD;
    }

    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken());
            sb.append(countValidPermutations(n)).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
