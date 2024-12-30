package be.springboot.pp.dsalgo.sorting;

public class S002_gfg_efficient_sorting {
    public void sort(int[] arr, int n) {
        // Auxiliary array to store sorted results in each pass
        int[] aux = new int[n];

        // Step 1: Sort based on the least significant digit (units place, mod n)
        countingSort(arr, aux, n, 1);

        // Step 2: Sort based on the middle digit (n-th place, divided by n)
        countingSort(arr, aux, n, n);

        // Step 3: Sort based on the most significant digit (n^2-th place, divided by n^2)
        countingSort(arr, aux, n, n * n);
    }

    // A helper function to perform counting sort on a specific digit
    private void countingSort(int[] arr, int[] aux, int n, int divisor) {
        int[] count = new int[n]; // Frequency array for digits [0, n-1]

        // Step 1: Count occurrences of each digit in the current place value
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / divisor) % n;
            count[digit]++;
        }

        // Step 2: Compute prefix sums to determine the correct positions
        for (int i = 1; i < n; i++) count[i] += count[i - 1];

        // Step 3: Place elements into the auxiliary array based on the current digit
        for (int i = n - 1; i >= 0; i--) { // Traverse in reverse for stability
            int digit = (arr[i] / divisor) % n;
            aux[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Step 4: Copy sorted elements back to the original array
        for (int i = 0; i < n; i++) arr[i] = aux[i];
    }
}
