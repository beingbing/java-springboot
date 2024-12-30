package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class S002_sp_kaos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] words = new String[n];
        String[] reversedWords = new String[n];

        // Read words and their reversed versions
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
            reversedWords[i] = new StringBuilder(words[i]).reverse().toString();
        }

        // Sort reversed words and retain their original indices
        Map<String, Integer> indexMap = new HashMap<>();
        String[] sortedReversed = reversedWords.clone();
        Arrays.sort(sortedReversed);

        for (int i = 0; i < n; i++) indexMap.put(sortedReversed[i], i);

        // Transform original words into their corresponding indices
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) indices[i] = indexMap.get(reversedWords[i]);

        // Count inversions in the index array
        long chaosDegree = countInversions(indices, 0, n - 1);
        System.out.println(chaosDegree);
    }

    // Merge sort to count inversions
    private static long countInversions(int[] arr, int left, int right) {
        long inversions = 0;
        if (left >= right) return inversions;

        int mid = left + (right - left) / 2;

        // Count inversions in the left and right halves
        inversions += countInversions(arr, left, mid);
        inversions += countInversions(arr, mid + 1, right);

        // Count cross-inversions during merge
        inversions += mergeAndCount(arr, left, mid, right);

        return inversions;
    }

    // Merge function to count cross-inversions and sort the array
    private static long mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        long inversions = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else {
                temp[k++] = arr[j++];
                // All elements from i to mid are inversions with arr[j]
                inversions += (mid - i + 1);
            }
        }

        // Copy remaining elements from the left half
        while (i <= mid) temp[k++] = arr[i++];

        // Copy remaining elements from the right half
        while (j <= right) temp[k++] = arr[j++];

        // Copy the sorted array back into the original array
        System.arraycopy(temp, 0, arr, left, temp.length);

        return inversions;
    }
}
