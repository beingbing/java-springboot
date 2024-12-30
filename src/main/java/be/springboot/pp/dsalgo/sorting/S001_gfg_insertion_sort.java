package be.springboot.pp.dsalgo.sorting;

class S001_gfg_insertion_sort {
    // Function to sort the array using insertion sort
    public static void insertionSort(int[] arr, int n) {
        // Insert the element at index i into the sorted section
        for (int i = 1; i < n; i++) insert(arr, i);
    }

    // Function to insert element at index i into the sorted section
    public static void insert(int[] arr, int i) {
        // Store the current element
        int key = arr[i];
        int j = i - 1;

        // Shift elements of the sorted section that are greater than key
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }

        // Place the key in its correct position
        arr[j + 1] = key;
    }

    // Driver function to test the algorithm
    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        int n = arr.length;

        insertionSort(arr, n);

        // Print the sorted array
        for (int num : arr) System.out.print(num + " ");
    }
}
