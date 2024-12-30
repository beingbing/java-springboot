package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S000_count_sort {
    public static void countSort(int[] a) {
        int n = a.length;
        if (n == 0) return;

        // Step 1: Find the range of the input
        // automatically accommodates -ve elements as well
        int min = Arrays.stream(a).min().getAsInt();
        int max = Arrays.stream(a).max().getAsInt();
        int range = max - min + 1;

        // Step 2: Create and populate the count array
        int[] count = new int[range];
        for (int num : a) count[num - min]++;

        // Step 3: Compute cumulative count
        // it will determine the positions of elements in the sorted array
        // and avoids iterating over the full frequency array unnecessarily.
        // as you should be iterating over provided array not the frequency of elements
        for (int i = 1; i < range; i++) count[i] += count[i - 1];

        // Step 4: Sort the elements into an output array
        int[] sorted = new int[n];
        // iterating input-array in reverse ensures stability
        for (int i = n - 1; i >= 0; i--) {
            int num = a[i];
            sorted[count[num - min] - 1] = num;
            count[num - min]--;
        }

        // Step 5: Copy sorted elements back to the original array
        System.arraycopy(sorted, 0, a, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Original array: " + Arrays.toString(arr));
        countSort(arr);
        System.out.println("Sorted array:   " + Arrays.toString(arr));
    }
}