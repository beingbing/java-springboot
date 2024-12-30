package be.springboot.pp.dsalgo.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S002_lc_1424 {
    public List<Integer> findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> diagonalMap = new HashMap<>();

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int diagonalIndex = i + j;
                diagonalMap.putIfAbsent(diagonalIndex, new ArrayList<>());
                diagonalMap.get(diagonalIndex).add(nums.get(i).get(j));
            }
        }

        // Step 3: Prepare the result list
        List<Integer> result = new ArrayList<>();

        // Step 4: Traverse the diagonals in order
        for (Integer d : diagonalMap.keySet()) {
            if (diagonalMap.containsKey(d)) {
                Collections.reverse(diagonalMap.get(d));
                result.addAll(diagonalMap.get(d));
            }
        }

        return result;
    }
}
