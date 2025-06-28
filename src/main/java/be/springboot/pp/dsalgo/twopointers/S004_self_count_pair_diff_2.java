package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S004_self_count_pair_diff_2 {
    public static int countPairsWithDifference(int[] arr, int K) {
        Arrays.sort(arr); // Step 1: Sort the array

        int count = 0;
        int i = 0, j = 1;

        while (i < arr.length && j < arr.length) {
            int diff = arr[j] - arr[i];

            if (diff == K) {
                if (K == 0) {
                    // Count pairs for K = 0
                    int freq = 0, duplicate = arr[i];
                    while (i < arr.length && arr[i] == duplicate) {
                        freq++;
                        i++;
                    }
                    count += (freq * (freq - 1)) / 2; // Number of ways to pick 2 from freq
                    j = i + 1; // Move to the next distinct number
                } else {
                    // Count duplicates around i and j
                    int countI = 1, countJ = 1;

                    while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                        countI++;
                        i++;
                    }

                    while (j + 1 < arr.length && arr[j] == arr[j + 1]) {
                        countJ++;
                        j++;
                    }

                    // Total pairs contributed by duplicates
                    count += countI * countJ;

                    // Move pointers
                    i++;
                    j++;
                }
            } else if (diff < K) {
                j++; // Increase the difference
            } else {
                i++; // Decrease the difference
                if (i == j) j++; // Ensure j stays ahead of i
            }
        }

        return count;
    }

    private int findAllDiffPairs(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        int left = 0, right = 1;
        int count = 0;

        while (right < n) {
            int diff = a[right] - a[left];

            if (diff == k) {
                int leftCount = 1, curLeft = a[left];
                int rightCount = 1, curRight = a[right];

                while (left < n && a[left] == curLeft) {
                    left++;
                    leftCount++;
                }

                while (right < n && a[right] == curRight) {
                    right++;
                    rightCount++;
                }

                if (curLeft == curRight) count += leftCount * (leftCount - 1) / 2;
                else count += leftCount * rightCount;
            } else if (diff < k) right++;
            else left++;

            if (left >= right) right = left + 1;
        }

        return count;
    }
}
