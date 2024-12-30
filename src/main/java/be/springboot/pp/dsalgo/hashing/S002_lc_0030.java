package be.springboot.pp.dsalgo.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S002_lc_0030 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) return result;

        int wordLength = words[0].length();
        int wordCount = words.length;

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);

        /*
        * Iterate over all possible starting points of sliding-window within the range of one word's length
        * These will be the only true unique starting points of a sliding-window, as each will allow
        * window to break its substring into wordLength segments of -
        * left, left + wordLength, left + 2*wordLength, ....
        * where left can be 0,1,2,... wordLength.
        * After processing till wordLength, if left is put on wordLength+1 then all possible sliding windows
        * for this position were already covered in left = 0.
        * */
        for (int i = 0; i < wordLength; i++) {
            int left = i; // Left pointer for the sliding window
            int right = i; // Right pointer for the sliding window
            int count = 0; // Number of valid words currently matched in the window
            Map<String, Integer> windowFrequency = new HashMap<>();

            while (right + wordLength <= s.length()) { // Continue while the right pointer can extract a complete word
                String word = s.substring(right, right + wordLength); // Extract a word from the right of the window
                right += wordLength; // move right-pointer forward to accommodate the extracted word in sliding-window

                if (wordFrequency.containsKey(word)) {
                    windowFrequency.put(word, windowFrequency.getOrDefault(word, 0) + 1);
                    count++;

                    while (windowFrequency.get(word) > wordFrequency.get(word)) { // If the frequency of word exceeds the required frequency, shrink the window
                        // Remove the leftmost word from the window
                        String leftWord = s.substring(left, left + wordLength);
                        windowFrequency.put(leftWord, windowFrequency.get(leftWord) - 1);
                        left += wordLength;
                        count--;
                    }

                    // If all words match, add the starting index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Reset the window if the word is not in the dictionary
                    windowFrequency.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return result;
    }
}
