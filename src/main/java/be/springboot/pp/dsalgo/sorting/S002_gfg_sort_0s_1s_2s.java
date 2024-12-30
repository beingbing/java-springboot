package be.springboot.pp.dsalgo.sorting;

public class S002_gfg_sort_0s_1s_2s {
    public static void sortArray(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;

        // Loop until mid crosses high
        while (mid <= high) {
            if (arr[mid] == 0) {
                // Swap arr[low] and arr[mid], increment low and mid
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            } else if (arr[mid] == 2) {
                // Swap arr[mid] and arr[high], decrement high
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            } else {
                // If arr[mid] == 1, just increment mid
                mid++;
            }
        }
    }
}
