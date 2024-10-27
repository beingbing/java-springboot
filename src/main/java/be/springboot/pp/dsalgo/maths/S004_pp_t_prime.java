package be.springboot.pp.dsalgo.maths;

import java.util.Scanner;

public class S004_pp_t_prime {

    private static boolean isTPrime(long n) {
        long val = (long) Math.sqrt(n);
        if (val*val != n) return false;
        int count = 2;
        for (int i = 2; i <= val; i++) {
            if (count > 3) return false;
            if (n%i == 0) {
                if (i == n/i) count++;
                else count += 2;
            }
        }
        if (count == 3) return true;
        return false;
    }

    public static void main(String[] args) {
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