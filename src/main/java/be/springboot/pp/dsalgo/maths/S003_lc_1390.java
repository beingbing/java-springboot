package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S003_lc_1390 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        boolean[] isPrime = sieve(1000000);
        Set<Integer> primeSet = new LinkedHashSet<>();
        for (int i = 2; i < isPrime.length; i++) if (isPrime[i]) primeSet.add(i);

        int total = 0;

        for (int num : nums) total += sumIfHasFourDivisors(num, primeSet);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(total + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    // Check if number has exactly 4 divisors and return their sum
    static int sumIfHasFourDivisors(int n, Set<Integer> primeSet) {
        // Case 1: n = p^3
        int root = (int)Math.round(Math.pow(n, 1.0 / 3));
        if (Math.pow(root, 3) == n && primeSet.contains(root)) {
            return 1 + root + root * root + n;
        }

        // Case 2: n = p * q (distinct primes)
        for (int p : primeSet) {
            if (p * p > n) break;
            if (n % p == 0) {
                int q = n / p;
                if (q != p && primeSet.contains(q)) {
                    return 1 + p + q + n;
                }
            }
        }

        return 0;
    }
}
