package be.springboot.pp.dsalgo.sorting;

public class S001_gfg_quick_sort {
    // Function to sort an array using Quick Sort
    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        // Partition the array and get the pivot index
        int pivotIndex = partition(arr, low, high);

        // Recursively sort elements before and after partition
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    // Partition function to rearrange elements around the pivot
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Pivot element
        int i = low; // Index of the smaller element

        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                swap(arr, i, j); // Swap elements
                i++; // Increment the index of the smaller element
            }
        }

        // Place pivot in the correct position
        swap(arr, i, high);
        return i;
    }

    // Utility function to swap two elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
