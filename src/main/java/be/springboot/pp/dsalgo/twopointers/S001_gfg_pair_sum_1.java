package be.springboot.pp.dsalgo.twopointers;

import java.util.HashSet;
import java.util.Scanner;

public class S001_gfg_pair_sum_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: N (size of array) and K (target sum)
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        // Call the function and print the result
        System.out.println(hasPairWithSum(arr, N, K) ? "Yes" : "No");
    }

    public static boolean hasPairWithSum(int[] arr, int N, int K) {
        // HashSet to store visited numbers
        HashSet<Integer> seen = new HashSet<>();

        // Traverse the array
        for (int num : arr) {
            // Check if the complement exists
            int complement = K - num;
            if (seen.contains(complement)) return true; // Found a pair
            // Add the current number to the set
            seen.add(num);
        }

        // If no pair is found
        return false;
    }
}
