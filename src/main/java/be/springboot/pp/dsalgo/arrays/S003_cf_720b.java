package be.springboot.pp.dsalgo.arrays;

import java.util.Scanner;

// problem statement is difficult to understand
// freq * num is extra computation
// otherwise same as first example question

public class S003_cf_720b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of flowers (n) and number of subarrays (m)
        int arraySize = sc.nextInt();
        int queriesCount = sc.nextInt();

        // Input: moods of the flowers
        int[] arrayElements = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            arrayElements[i] = sc.nextInt();
        }
        
        int[] prefixSubarraySum = new int[arraySize];
        prefixSubarraySum[0] = arrayElements[0];

        for (int i = 1; i < arraySize; i++) prefixSubarraySum[i] = prefixSubarraySum[i-1] + arrayElements[i];

        // Array to keep track of how many times each flower is in chosen subarrays
        int[] eachElementFrequency = new int[arraySize];

        // Process each suggested subarray
        for (int i = 0; i < queriesCount; i++) {
            int leftIndex = sc.nextInt() - 1;  // Start of the subarray (1-indexed to 0-indexed)
            int rightIndex = sc.nextInt() - 1;  // End of the subarray (1-indexed to 0-indexed)

            // Calculate the sum of moods for the subarray a[l:r+1]
            int subarraySum = prefixSubarraySum[rightIndex] - (leftIndex > 0 ? prefixSubarraySum[leftIndex - 1] : 0);

            // Only include the subarray if it contributes positively to happiness
            if (subarraySum > 0) {
                // Increment the contribution for all flowers in the subarray
                for (int j = leftIndex; j <= rightIndex; j++) {
                    eachElementFrequency[j]++;
                }
            }
        }

        // Calculate the total happiness
        int totalHappiness = 0;
        for (int i = 0; i < arraySize; i++) {
            totalHappiness += arrayElements[i] * eachElementFrequency[i];
        }

        // Output the maximum possible happiness
        System.out.print(totalHappiness);
    }
}
