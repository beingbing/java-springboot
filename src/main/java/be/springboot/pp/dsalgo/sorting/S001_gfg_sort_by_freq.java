package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class S001_gfg_sort_by_freq {
    private static int[] getSortedArray(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums)
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);

        // Custom sorting logic
        List<Integer> uniqueElements = new ArrayList<>(frequencyMap.keySet());
        uniqueElements.sort((a, b) -> {
            int freqA = frequencyMap.get(a);
            int freqB = frequencyMap.get(b);
            if (freqA != freqB) return freqB - freqA; // Descending order of frequency
            return a - b; // Ascending order of value for ties
        });

        // Reconstruct the sorted array
        List<Integer> sortedArray = new ArrayList<>();
        for (int num : uniqueElements) {
            int frequency = frequencyMap.get(num);
            for (int i = 0; i < frequency; i++) {
                sortedArray.add(num);
            }
        }
        return sortedArray.stream().mapToInt(i -> i).toArray();
    }
}
