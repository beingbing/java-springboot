package be.springboot.pp.dsalgo.sorting;

class S002_gfg_bubble_sort {
    // Function to sort the array using Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        // Outer loop for passes
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Track if any swaps occur in this pass

            // Inner loop for comparing adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap if elements are in the wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true; // Mark that a swap occurred
                }
            }

            // If no swaps occurred, the array is already sorted
            if (!swapped) break;
        }
    }

    public void bubble(int[] a) {
        int n = a.length;
        boolean swapped; // Track if any swaps occur in this pass

        // Outer loop for passes
        for (int right = 0; right < n; right++) {
            swapped = false;

            // Inner loop for comparing adjacent elements
            for (int left = 0; left < n - right - 1; left++) {
                if (a[left] <= a[left + 1]) continue;
                // Swap if elements are in the wrong order
                int tmp = a[left];
                a[left] = a[left + 1];
                a[left + 1] = tmp;
                swapped = true; // Mark that a swap occurred
            }
            if (!swapped) break; // If no swaps occurred, the array is already sorted
        }
    }
}
