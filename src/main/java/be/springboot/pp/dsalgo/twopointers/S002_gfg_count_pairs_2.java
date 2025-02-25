package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S002_gfg_count_pairs_2 {
    public static int findPairsWithSum(int[] a, int N, int K) {
        Arrays.sort(a);

        int left = 0, right = N - 1, count = 0;

        while (left < right) {
            int sum = a[left] + a[right];

            if (sum < K) left++;
            else if (sum > K) right--;
            else {
                int countLeft = 1, countRight = 1;

                while (left < right && a[left] == a[left + 1]) {
                    countLeft++;
                    left++;
                }

                while (left < right && a[right] == a[right - 1]) {
                    countRight++;
                    right--;
                }

                if (a[left] == a[right])
                    count += (countLeft * (countLeft - 1)) / 2;
                else count += countLeft * countRight;

                left++;
                right--;
            }
        }

        return count;
    }
}
