package be.springboot.pp.dsalgo.sorting;

import java.util.Scanner;

public class S001_gfg_merge_sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        // Perform merge sort
        mergeSort(arr, 0, n - 1);

        for (int num : arr) System.out.print(num + " ");
        sc.close();
    }

    // Recursive merge sort function
    private static void mergeSort(int[] arr, int left, int right) {
        if (left == right) return;

        // Find the midpoint
        int mid = left + (right - left) / 2;

        // Recursively sort the left half
        mergeSort(arr, left, mid);

        // Recursively sort the right half
        mergeSort(arr, mid + 1, right);

        // Merge the two sorted halves
        merge(arr, left, mid, right);
    }

    // Merge two sorted subarrays
    private static void merge(int[] arr, int left, int mid, int right) {
        // Sizes of two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int i = 0; i < n2; i++) rightArr[i] = arr[mid + 1 + i];

        // Merge the temporary arrays back into arr
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2)
            if (leftArr[i] <= rightArr[j]) arr[k++] = leftArr[i++];
            else arr[k++] = rightArr[j++];

        // Copy any remaining elements from leftArr
        while (i < n1) arr[k++] = leftArr[i++];

        // Copy any remaining elements from rightArr
        while (j < n2) arr[k++] = rightArr[j++];
    }
}