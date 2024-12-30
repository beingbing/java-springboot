package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S001_gfg_segregate_ev_od {
    public static void segregateAndSort(int[] arr) {
        int n = arr.length;
        int evenIndex = 0;

        // Step 1: Segregate even and odd numbers
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) { // If the number is even
                // Swap to move even number to the front
                int temp = arr[evenIndex];
                arr[evenIndex] = arr[i];
                arr[i] = temp;
                evenIndex++;
            }
        }

        // Step 2: Sort the even part (0 to evenIndex-1)
        Arrays.sort(arr, 0, evenIndex);

        // Step 3: Sort the odd part (evenIndex to n-1)
        Arrays.sort(arr, evenIndex, n);
    }
}
