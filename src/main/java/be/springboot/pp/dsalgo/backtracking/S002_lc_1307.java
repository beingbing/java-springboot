package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class S002_lc_1307 {
    private Map<Character, Integer> charToDigit = new HashMap<>();
    private boolean[] usedDigits = new boolean[10];
    private Set<Character> leadingChars = new HashSet<>();

    public boolean isSolvable(String[] words, String result) {
        Set<Character> uniqueChars = new HashSet<>();

        // Collect all unique characters
        for (String word : words) {
            for (char ch : word.toCharArray()) uniqueChars.add(ch);
            leadingChars.add(word.charAt(0)); // Leading chars cannot map to 0
        }

        for (char ch : result.toCharArray()) uniqueChars.add(ch);
        leadingChars.add(result.charAt(0));

        // If we have more than 10 unique characters, it's impossible to map them uniquely
        if (uniqueChars.size() > 10) return false;

        List<Character> characters = new ArrayList<>(uniqueChars);
        return backtrack(0, characters, words, result);
    }

    // Backtracking function to assign digits to characters
    private boolean backtrack(int index, List<Character> characters, String[] words, String result) {
        // Base case: all characters are assigned
        if (index == characters.size()) {
            return checkSolution(words, result);
        }

        char ch = characters.get(index);
        for (int digit = 0; digit < 10; digit++) {
            // Avoid using the same digit or assigning zero to a leading character
            if (usedDigits[digit] || (digit == 0 && leadingChars.contains(ch))) continue;

            // Try assigning digit to character ch
            charToDigit.put(ch, digit);
            usedDigits[digit] = true;

            if (backtrack(index + 1, characters, words, result)) return true;

            // Backtrack
            charToDigit.remove(ch);
            usedDigits[digit] = false;
        }
        return false;
    }

    // Check if the current mapping satisfies the equation
    private boolean checkSolution(String[] words, String result) {
        int sum = 0;

        // Calculate the sum of all words as numbers
        for (String word : words) {
            int wordValue = getWordValue(word);
            if (wordValue == -1) return false; // Invalid mapping (e.g., leading zero)
            sum += wordValue;
        }

        // Calculate the result as a number
        int resultValue = getWordValue(result);
        return resultValue != -1 && sum == resultValue;
    }

    // Convert a word to its integer value based on current char-to-digit mapping
    private int getWordValue(String word) {
        int value = 0;
        for (char ch : word.toCharArray()) {
            if (!charToDigit.containsKey(ch)) return -1;
            value = value * 10 + charToDigit.get(ch);
        }
        // Check for leading zero
        if (word.length() > 1 && charToDigit.get(word.charAt(0)) == 0) return -1;
        return value;
    }
}