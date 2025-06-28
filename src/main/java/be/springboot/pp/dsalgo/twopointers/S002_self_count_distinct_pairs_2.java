package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S002_self_count_distinct_pairs_2 {
    public static int countDistinctPairs(int[] arr, int K) {
        Arrays.sort(arr); // Step 1: Sort the array
        int left = 0, right = arr.length - 1;
        int count = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == K) {
                count++; // Valid pair found
                left++;
                right--;

                // Skip duplicates on both ends
                while (left < right && arr[left] == arr[left - 1]) left++;
                while (left < right && arr[right] == arr[right + 1]) right--;
            } else if (sum < K) left++; // Increase the sum
            else right--; // Decrease the sum
        }

        return count;
    }

    public int findDistinctPairs(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        int left = 0, right = n - 1;
        int count = 0;

        while (left < right) {
            int sum = a[left] + a[right];

            if (sum == k) {
                count++;
                int curLeft = left;
                int curRight = right;

                while (left < right && a[left] == curLeft) left++;
                while (left < right && a[right] == curRight) right--;
            } else if (sum < k) left++;
            else right--;
        }

        return count;
    }
}
