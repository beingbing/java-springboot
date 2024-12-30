package be.springboot.pp.dsalgo.sorting;

import java.util.Scanner;

public class S001_gfg_max_index {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        // Preprocessing: Create leftMin and rightMax arrays
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];

        // Populate leftMin array
        leftMin[0] = arr[0];
        for (int i = 1; i < n; i++)
            leftMin[i] = Math.min(leftMin[i - 1], arr[i]);

        // Populate rightMax array
        rightMax[n - 1] = arr[n - 1];
        for (int j = n - 2; j >= 0; j--)
            rightMax[j] = Math.max(rightMax[j + 1], arr[j]);

        // Two-pointer technique to find maximum j - i
        int i = 0, j = 0, maxDiff = -1;
        while (i < n && j < n) {
            if (leftMin[i] <= rightMax[j]) {
                maxDiff = Math.max(maxDiff, j - i);
                j++; // Expand the window
            } else i++; // Shrink the window
        }

        System.out.println(maxDiff);
    }
}
