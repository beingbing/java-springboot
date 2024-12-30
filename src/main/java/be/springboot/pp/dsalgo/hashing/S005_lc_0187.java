package be.springboot.pp.dsalgo.hashing;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class S005_lc_0187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 10) return result; // If length is less than 10, no substrings of size 10 exist

        // Map to store frequencies of 10-character sequences
        Map<String, Integer> sequenceCount = new HashMap<>();

        // Sliding window approach
        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10); // Extract 10-character substring
            sequenceCount.put(substring, sequenceCount.getOrDefault(substring, 0) + 1);
        }

        // Add all substrings that appear more than once to the result
        for (Map.Entry<String, Integer> entry : sequenceCount.entrySet())
            if (entry.getValue() > 1) result.add(entry.getKey());

        return result;
    }
}
