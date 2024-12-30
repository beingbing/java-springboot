package be.springboot.pp.dsalgo.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S001_lc_0498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        // Step 2: Use a map to group elements by their diagonal index (i + j)
        Map<Integer, List<Integer>> diagonalMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diagonalIndex = i + j;
                diagonalMap.putIfAbsent(diagonalIndex, new ArrayList<>());
                diagonalMap.get(diagonalIndex).add(mat[i][j]);
            }
        }

        int[] result = new int[m * n];
        int index = 0;

        // Step 4: Traverse the diagonals in order
        for (int d = 0; d < m + n - 1; d++) {
            List<Integer> diagonal = diagonalMap.get(d);

            if (d % 2 == 0) {
                // Reverse for even diagonals
                for (int i = diagonal.size() - 1; i >= 0; i--)
                    result[index++] = diagonal.get(i);
            } else {
                // Append directly for odd diagonals
                for (Integer integer : diagonal) result[index++] = integer;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        S001_lc_0498 obj = new S001_lc_0498();
        int[] ans = obj.findDiagonalOrder(mat);
        for (int ele : ans)
            System.out.print(ele + " ");
        System.out.println();
    }
}
