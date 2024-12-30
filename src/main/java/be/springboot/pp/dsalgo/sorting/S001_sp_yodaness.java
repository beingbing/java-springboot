package be.springboot.pp.dsalgo.sorting;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class S001_sp_yodaness {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases

        while (t-- > 0) {
            int n = scanner.nextInt(); // Number of words
            scanner.nextLine();

            String[] yodaSentence = scanner.nextLine().split(" ");
            String[] normalSentence = scanner.nextLine().split(" ");

            // Map each word in the normal sentence to its index
            Map<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < n; i++) indexMap.put(normalSentence[i], i);

            // Transform Yoda's sentence into an array of indices
            int[] indices = new int[n];
            for (int i = 0; i < n; i++) indices[i] = indexMap.get(yodaSentence[i]);

            // Count inversions using Merge Sort
            int inversions = countInversions(indices, 0, n - 1);
            System.out.println(inversions);
        }

        scanner.close();
    }

    // Function to count inversions using Merge Sort
    private static int countInversions(int[] arr, int left, int right) {
        int inversions = 0;

        if (left >= right) return inversions;

        int mid = left + (right - left) / 2;

        // Count inversions in the left half
        inversions += countInversions(arr, left, mid);

        // Count inversions in the right half
        inversions += countInversions(arr, mid + 1, right);

        // Count cross-inversions and merge the two halves
        inversions += mergeAndCount(arr, left, mid, right);

        return inversions;
    }

    // Merge function to count cross-inversions and sort the array
    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        int inversions = 0;

        // Merge the two halves
        while (i <= mid && j <= right)
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else {
                // arr[i] > arr[j], so all elements from arr[i] to arr[mid] form inversions with arr[j]
                temp[k++] = arr[j++];
                inversions += (mid - i + 1);
            }

        // Copy remaining elements from the left half
        while (i <= mid) temp[k++] = arr[i++];

        // Copy remaining elements from the right half
        while (j <= right) temp[k++] = arr[j++];

        // Copy sorted elements back into the original array
        for (i = left, k = 0; i <= right; i++, k++) arr[i] = temp[k];

        return inversions;
    }
}
