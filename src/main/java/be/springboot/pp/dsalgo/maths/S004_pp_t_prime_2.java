package be.springboot.pp.dsalgo.maths;

import java.util.Scanner;

public class S004_pp_t_prime_2 {

    private static boolean isPerfectSquare(int n) {
        int val = (int) Math.sqrt(n);
        return val*val == n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        while (n-- > 0) {
            int ele = sc.nextInt();

            if (ele == 1) {
                System.out.println("NO");
                continue;
            }

            if (ele == 4) {
                System.out.println("YES");
                continue;
            }

            if (isPerfectSquare(ele) && ele % 2 != 0) System.out.println("YES");
            else System.out.println("NO");
        }

        sc.close();
    }
}