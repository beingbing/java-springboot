package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S002_lc_0973_b {
    public int[][] kClosest(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private void quickSelect(int[][] points, int left, int right, int k) {
        if (left >= right) return;

        int pivotIndex = partition(points, left, right);
        int count = pivotIndex - left + 1;

        if (count == k) return;
        if (count < k) quickSelect(points, pivotIndex + 1, right, k - count);
        else quickSelect(points, left, pivotIndex - 1, k);
    }

    private int partition(int[][] points, int left, int right) {
        int[] pivot = points[right];
        int pivotDist = distanceSquared(pivot);
        int i = left;

        for (int j = left; j < right; j++) {
            if (distanceSquared(points[j]) <= pivotDist) {
                swap(points, i, j);
                i++;
            }
        }

        swap(points, i, right);
        return i;
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    private int distanceSquared(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
