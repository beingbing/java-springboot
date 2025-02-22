package be.springboot.pp.dsalgo.searching;

import java.util.Arrays;

public class S001_sp_aggrcow {
    private static boolean canPlaceCows(int[] stalls, int cows, int placing) {
        int count = 1;
        int lastPos = stalls[0];  // Position of the last placed cow

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= placing) {
                count++;
                lastPos = stalls[i];
                if (count == cows) return true;
            }
        }
        return false;
    }

    public static int largestMinimumDistance(int[] stalls, int cows) {
        Arrays.sort(stalls);

        int n = stalls.length;
        int left = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++)
            left = Math.min(left, stalls[i] - stalls[i - 1]);

        int right = stalls[n - 1] - stalls[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canPlaceCows(stalls, cows, mid)) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }
}
