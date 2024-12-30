package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;

public class S002_lc_0076 {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) return ""; // Edge case: t is longer than s

        // HashMap to store the frequency of characters in t
        HashMap<Character, Integer> targetFrequencyMap = new HashMap<>();
        for (char c : t.toCharArray()) targetFrequencyMap.put(c, targetFrequencyMap.getOrDefault(c, 0) + 1);

        int requiredDistinctChars = targetFrequencyMap.size(); // Number of unique characters needed
        int validCharCount = 0; // tracks number of unique characters in the current window that satisfy frequency
        // Variables for sliding window and result tracking
        int left = 0, right = 0, minWindowLength = Integer.MAX_VALUE;
        int startOfMinWindow = 0; // Start index of the minimum window

        // HashMap to store the frequency of characters in the current window
        HashMap<Character, Integer> windowFrequencyMap = new HashMap<>();

        while (right < s.length()) {
            // Include the current character in the window
            char current = s.charAt(right);
            windowFrequencyMap.put(current, windowFrequencyMap.getOrDefault(current, 0) + 1);

            // Check if this character satisfies the frequency requirement of t
            if (targetFrequencyMap.containsKey(current) && windowFrequencyMap.get(current).intValue() == targetFrequencyMap.get(current).intValue()) validCharCount++;

            // Try to shrink the window from the left
            while (left <= right && validCharCount == requiredDistinctChars) {
                // Update the result if the current window is smaller
                if (right - left + 1 < minWindowLength) {
                    minWindowLength = right - left + 1;
                    startOfMinWindow = left;
                }

                // Remove the left character from the window
                char leftChar = s.charAt(left);
                windowFrequencyMap.put(leftChar, windowFrequencyMap.get(leftChar) - 1);

                // Check if the removal invalidates the window
                if (targetFrequencyMap.containsKey(leftChar) && windowFrequencyMap.get(leftChar) < targetFrequencyMap.get(leftChar)) validCharCount--;

                left++; // Move the left pointer
            }

            right++; // Expand the window
        }

        // Return the minimum window substring
        return minWindowLength == Integer.MAX_VALUE ? "" : s.substring(startOfMinWindow, startOfMinWindow + minWindowLength);
    }
}
