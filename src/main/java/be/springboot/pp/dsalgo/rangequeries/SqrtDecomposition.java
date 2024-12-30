package be.springboot.pp.dsalgo.rangequeries;

import java.util.Arrays;

public class SqrtDecomposition {
    int[] blocks, arr;
    int blockSize;

    public SqrtDecomposition(int[] input) {
        int n = input.length;
        arr = input;
        blockSize = (int) Math.sqrt(n) + 1;
        blocks = new int[blockSize];
        Arrays.fill(blocks, Integer.MAX_VALUE);

        // Precompute block minimums
        for (int i = 0; i < n; i++) blocks[i / blockSize] = Math.min(blocks[i / blockSize], arr[i]);
    }

    int query(int l, int r) {
        int minValue = Integer.MAX_VALUE;

        // Traverse partial block on the left
        while (l <= r && l % blockSize != 0) {
            minValue = Math.min(minValue, arr[l]);
            l++;
        }

        // Traverse complete blocks in the middle
        while (l + blockSize - 1 <= r) {
            minValue = Math.min(minValue, blocks[l / blockSize]);
            l += blockSize;
        }

        // Traverse partial block on the right
        while (l <= r) {
            minValue = Math.min(minValue, arr[l]);
            l++;
        }

        return minValue;
    }
}
