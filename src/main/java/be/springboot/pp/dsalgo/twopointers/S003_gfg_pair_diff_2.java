package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S003_gfg_pair_diff_2 {
    public String hasPairWithDifference(int[] arr, int N, int K) {
        Arrays.sort(arr);

        int left = 0, right = 1;

        while (right < N) {
            if (left == right) {
                right++;
                continue;
            }

            int diff = arr[right] - arr[left];

            if (diff == K) return "Yes";
            else if (diff < K) right++;
            else left++;
        }

        return "No";
    }
}
