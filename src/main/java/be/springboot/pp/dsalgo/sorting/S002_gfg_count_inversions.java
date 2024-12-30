package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S002_gfg_count_inversions {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            long[] arr = new long[n];
            String[] inputs = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(inputs[i]);
            bw.write(mergeSortAndCount(arr, 0, n - 1) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // Merge Sort function to count inversions
    private static long mergeSortAndCount(long[] arr, int left, int right) {
        long count = 0;

        if (left == right) return count;

        // Find the midpoint
        int mid = left + (right - left) / 2;

        // Count inversions in the left half
        count += mergeSortAndCount(arr, left, mid);

        // Count inversions in the right half
        count += mergeSortAndCount(arr, mid + 1, right);

        // Count inversions during merge
        count += mergeAndCount(arr, left, mid, right);

        return count;
    }

    // Merge function to count inversions during merging
    private static long mergeAndCount(long[] arr, int left, int mid, int right) {
        // Sizes of two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        long[] leftArr = new long[n1];
        long[] rightArr = new long[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int i = 0; i < n2; i++) rightArr[i] = arr[mid + 1 + i];

        // Merge the two subarrays and count inversions
        int i = 0, j = 0, k = left;
        long count = 0;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) arr[k++] = leftArr[i++];
            else {
                arr[k++] = rightArr[j++];
                // Count inversions: all remaining elements in leftArr are greater
                count += (n1 - i);
            }
        }

        // Copy remaining elements from leftArr
        while (i < n1) arr[k++] = leftArr[i++];

        // Copy remaining elements from rightArr
        while (j < n2) arr[k++] = rightArr[j++];

        return count;
    }
}
