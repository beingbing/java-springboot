package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S000_range_queries {
    // Function to process range queries and return the final array
    public static int[] processQueries(int n, int[][] queries) {
        int[] aux = new int[n + 1];

        // Mark increments and decrements in the auxiliary array
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            aux[l] += 1;
            if (r + 1 < n) {
                aux[r + 1] -= 1;
            }
        }

        // Compute the prefix sum to get the final state of the array
        int[] result = new int[n];
        result[0] = aux[0];
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] + aux[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 5; // Size of the array
        int[][] queries = {
                {0, 2}, // Increment arr[0] to arr[2] by 1
                {1, 4}, // Increment arr[1] to arr[4] by 1
                {2, 3}  // Increment arr[2] to arr[3] by 1
        };

        int[] result = processQueries(n, queries);
        System.out.println("Final Array: " + Arrays.toString(result));
    }
}
