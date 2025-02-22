package be.springboot.pp.dsalgo.searching;

import java.util.Scanner;

public class S007_cf_670d1 {
    private static boolean canBake(int[] wtNeeded, int[] wtPresent, int ingredientsCnt, long xtra, long x) {
        long totalShortage = 0;

        for (int i = 0; i < ingredientsCnt; i++) {
            long required = x * wtNeeded[i];
            long shortage = Math.max(0, required - wtPresent[i]);
            totalShortage += shortage;

            if (totalShortage > xtra) return false; // Can't bake x cookies if shortage exceeds available powder
        }

        return true;
    }

    private static long getMaxPossible(int[] wtPresent, long xtra) {
        long maxIngredient = 0;
        for (int wt : wtPresent) maxIngredient = Math.max(maxIngredient, wt);
        return maxIngredient + xtra;
    }

    private static long maxCookies(int[] wtNeeded, int[] wtPresent, int ingredientsCnt, long xtra) {
        long left = 0, right = getMaxPossible(wtPresent, xtra);

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canBake(wtNeeded, wtPresent, ingredientsCnt, xtra, mid)) left = mid + 1;
            else right = mid - 1; // Reduce the range
        }

        return right;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        readInput(sc);
        System.out.println(maxCookies(a, b, n, k));
        sc.close();
    }

    private static int n;
    private static long k;
    private static int[] a, b;

    private static void readInput(Scanner sc) {
        n = sc.nextInt();
        k = sc.nextLong();
        a = new int[n];
        b = new int[n];

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int i = 0; i < n; i++) b[i] = sc.nextInt();
    }
}
