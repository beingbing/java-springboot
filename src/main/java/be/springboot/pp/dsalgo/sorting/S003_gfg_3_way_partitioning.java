package be.springboot.pp.dsalgo.sorting;

public class S003_gfg_3_way_partitioning {
    public static int threeWayPartition(int[] arr, int a, int b) {
        int start = 0;  // Pointer for elements < a
        int end = arr.length - 1;  // Pointer for elements > b
        int i = 0;  // Current index being evaluated

        // Loop until i crosses the end pointer
        while (i <= end) {
            if (arr[i] < a) {
                // Swap current element with the element at the start pointer
                int temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                start++;
                i++;
            } else if (arr[i] > b) {
                // Swap current element with the element at the end pointer
                int temp = arr[end];
                arr[end] = arr[i];
                arr[i] = temp;
                end--;
            } else {
                // Element is within the range [a, b], just move to the next
                i++;
            }
        }

        // Return 1 as a success flag (as required by the problem)
        return 1;
    }
}
