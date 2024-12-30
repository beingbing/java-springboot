package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class S003_cf_903c {
    private static int findMaxFrequency(int[] sideLengths) {
        // Frequency map to count occurrences of each side length
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int length : sideLengths)
            frequencyMap.put(length, frequencyMap.getOrDefault(length, 0) + 1);

        // Find the maximum frequency
        int maxFrequency = 0;
        for (int freq : frequencyMap.values()) {
            maxFrequency = Math.max(maxFrequency, freq);
        }
        return maxFrequency;
    }
}
