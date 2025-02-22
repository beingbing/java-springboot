package be.springboot.pp.dsalgo.searching;

import java.util.Arrays;
import java.util.Scanner;

public class S006_cf_812c {
    private static long calculateCost(int[] baseCosts, int n, int k) {
        long[] adjustedCosts = new long[n];
        for (int i = 0; i < n; i++)
            adjustedCosts[i] = (long) baseCosts[i] + (long) (i + 1) * k;

        Arrays.sort(adjustedCosts);

        long totalCost = 0;
        for (int i = 0; i < k; i++) totalCost += adjustedCosts[i];

        return totalCost;
    }

    public static int[] maxSouvenirs(int[] baseCosts, int n, long budget) {
        int left = 0, right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (calculateCost(baseCosts, n, mid) <= budget) left = mid + 1;
            else right = mid - 1; // Reduce the number of items if over budget
        }

        return new int[]{right, (int) calculateCost(baseCosts, n, right)}; // (max-items, min-cost)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        readInput(sc);
        int[] result = maxSouvenirs(baseCost, n, S);
        System.out.println(result[0] + " " + result[1]);
        sc.close();
    }

    private static int n;
    private static long S;
    private static int[] baseCost;

    private static void readInput(Scanner sc) {
        n = sc.nextInt();
        S = sc.nextLong();
        baseCost = new int[n];

        for (int i = 0; i < n; i++) baseCost[i] = sc.nextInt();
    }
}
