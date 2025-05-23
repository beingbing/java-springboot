package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class S005_pp_cf_1034a {
    static final int MAX = 15000000;
    static int[] spf = new int[MAX + 1];

    static void computeSPF() {
        for (int i = 2; i <= MAX; i++)
            if (spf[i] == 0)
                for (int j = i; j <= MAX; j += i)
                    if (spf[j] == 0) spf[j] = i;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        int g = 0;

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            g = gcd(g, nums[i]);
        }

        for (int i = 0; i < n; i++) nums[i] /= g;

        computeSPF();

        Map<Integer, Integer> primeCount = new HashMap<>();
        for (int num : nums) {
            while (num > 1) {
                int prime = spf[num];
                primeCount.put(prime, primeCount.getOrDefault(prime, 0) + 1);
                while (num % prime == 0) num /= prime;
            }
        }

        int maxFreq = primeCount.values().stream().max(Integer::compareTo).orElse(0);
        System.out.println(maxFreq == 0 ? -1 : n - maxFreq);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
