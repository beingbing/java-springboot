package be.springboot.pp.dsalgo.searching;

import java.util.Scanner;

public class S002_lc_1201 {

    // Function to find the Greatest Common Divisor (GCD)
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Function to find the Least Common Multiple (LCM)
    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // Function to find the nth magical number
    private static long nthNumber(int n, int a, int b, int c) {
        long left = 1;
        long right = (long) n * Math.min(a, Math.min(b, c));

        long lcmAB = lcm(a, b);
        long lcmBC = lcm(b, c);
        long lcmCA = lcm(c, a);
        long lcmABC = lcm(a, lcm(b, c)); // LCM of a, b, and c

        while (left < right) {
            long mid = left + (right - left) / 2;

            // Calculate count of numbers up to `mid` that are divisible by at least one of a, b, or c
            long count = (mid / a) + (mid / b) + (mid / c)
                    - (mid / lcmAB) - (mid / lcmBC) - (mid / lcmCA)
                    + (mid / lcmABC);

            if (count < n) left = mid + 1;
            else right = mid;
        }

        return left; // The nth magical number
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            result.append(nthNumber(n, a, b, c)).append("\n");
        }

        System.out.print(result.toString());
        scanner.close();
    }
}
