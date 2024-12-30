package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;

public class S001_lc_0003 {
    public static int lengthOfLongestSubstring(String s) {
        // HashMap to store the last seen index of each character
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0; // Variable to track the longest substring length
        int left = 0; // Left boundary of the sliding window

        // Traverse the string
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character is already in the map and within the current window
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                // Move the left pointer to exclude the earlier occurrence
                left = charIndexMap.get(currentChar) + 1;
            }

            // Update the character's index in the map
            charIndexMap.put(currentChar, right);

            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength; // Return the length of the longest substring
    }
}
