package be.springboot.pp.dsalgo.sorting;

import java.util.Scanner;

public class S000_len_sm_sbar_sum_ge_k {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the size of the array and the target sum
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        // Input the array elements
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int result = findSmallestSubarray(arr, n, k);
        System.out.println(result);
    }

    public static int findSmallestSubarray(int[] a, int n, int k) {
        int start = 0, currentSum = 0, minLength = Integer.MAX_VALUE;

        // Traverse the array using the sliding window / Two Pointer
        for (int end = 0; end < n; end++) {
            // Add the current element to the sum
            currentSum += a[end];

            // Shrink the window as long as the sum is >= k
            while (currentSum >= k) {
                minLength = Math.min(minLength, end - start + 1);
                currentSum -= a[start];
                start++;
            }
        }

        // If no valid subarray was found, return 0
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}
