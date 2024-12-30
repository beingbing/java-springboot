package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S004_lc_1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Step 1: Build a map for positions of elements in arr2
        Map<Integer, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) positionMap.put(arr2[i], i);

        // Step 2: Partition arr1 into two lists
        List<Integer> inArr2 = new ArrayList<>();
        List<Integer> notInArr2 = new ArrayList<>();
        for (int num : arr1)
            if (positionMap.containsKey(num)) inArr2.add(num);
            else notInArr2.add(num);

        // Step 3: Sort elements in arr1 present in arr2 using the position map
        inArr2.sort(Comparator.comparingInt(positionMap::get));

        // Step 4: Sort elements not in arr2 in ascending order
        Collections.sort(notInArr2);

        // Step 5: Merge the two sorted lists
        int[] result = new int[arr1.length];
        int index = 0;
        for (int num : inArr2) result[index++] = num;
        for (int num : notInArr2) result[index++] = num;

        return result;
    }
}
