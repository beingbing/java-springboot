package be.springboot.pp.dsalgo.maths;

import java.util.Scanner;

public class S003_pp_divisor_summation {

    private static int properDivisorSum(int n) {
        if (n == 1) return 0;
        int sum = 1;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (i*i == n) sum += i;
                else {
                    sum += i;
                    sum += n/i;
                }
            }
        }
        return sum;
    }

    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(properDivisorSum(n));
        }

        sc.close();
    }
}