package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class S004_cf_231c {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: size of array and number of operations allowed
        int n = sc.nextInt();
        long k = sc.nextLong();

        // Input: the array
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        // Sort the array
        Arrays.sort(a);

        // Variables to track the result
        int maxFrequency = 1; // Minimum frequency is at least 1
        int minValue = a[0];  // Start with the smallest value

        // Sliding window pointers and running sum
        int left = 0;
        long currentSum = 0;

        for (int right = 0; right < n; right++) {
            // Add the current element to the running sum
            currentSum += a[right];

            // Calculate the cost to make all elements in the window equal to a[right]
            while ((long) a[right] * (right - left + 1) - currentSum > k) {
                currentSum -= a[left]; // Shrink the window from the left
                left++;
            }

            // Update maximum frequency and the smallest value
            int currentFrequency = right - left + 1;
            if (currentFrequency > maxFrequency) {
                maxFrequency = currentFrequency;
                minValue = a[right];
            }
        }

        // Output the result
        System.out.println(maxFrequency + " " + minValue);
    }
}
