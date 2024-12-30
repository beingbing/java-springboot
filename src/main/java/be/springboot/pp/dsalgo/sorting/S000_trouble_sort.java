package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S000_trouble_sort {
    public static void troubleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 2; j++) { // Compare elements two indices apart
                if (arr[j] > arr[j + 2]) { // Reverse if out of order
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
