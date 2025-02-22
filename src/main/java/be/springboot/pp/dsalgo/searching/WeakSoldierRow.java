package be.springboot.pp.dsalgo.searching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class WeakSoldierRow {

    private int countSoldiers(int[] row) {
        int n = row.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (row[mid] == 0) right = mid - 1;
            else left = mid + 1;
        }

        return right;
    }

    public int[] weakestRows(int[][] mat, int k) {
        int n = mat.length;

        Map<Integer, Integer> rowStrength = new HashMap<>();

        for (int i = 0; i < n; i++)
            rowStrength.put(i, countSoldiers(mat[i]));

        Map<Integer, Integer> sortedRowStrength = rowStrength
                .entrySet()
                .stream()
                .sorted((pair1, pair2) -> {
                    if (Objects.equals(pair1.getValue(), pair2.getValue())) return pair1.getKey() - pair2.getKey();
                    return pair1.getValue() - pair2.getValue();
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        int[] result = new int[k];
        int count = 0;

        Iterator<Integer> iterator = sortedRowStrength.keySet().iterator();
        while (iterator.hasNext() && count < k) result[count++] = iterator.next();

        return result;
    }

    public static void main(String[] args) {
        WeakSoldierRow solution = new WeakSoldierRow();

        // Test case input
        int[][] mat = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };

        // Expected output: [2, 0, 3, 1, 4]
        System.out.println(Arrays.toString(solution.weakestRows(mat, 3)));
    }
}
