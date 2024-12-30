package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S002_lc_0316 {
    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26]; // Frequency array to count occurrences of each character
        for (char c : s.toCharArray()) freq[c - 'a']++;

        boolean[] presentInStack = new boolean[26]; // Boolean array to track if a character is already in the stack
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            freq[index]--; // Decrement the frequency as the character is being processed

            if (presentInStack[index]) continue; // If character is already in the stack, skip it

            // Remove characters from stack if:
            // 1. They are lexicographically larger than the current character
            // 2. They will again appear later in the string
            while (!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] > 0) presentInStack[stack.pop() - 'a'] = false;

            // Add the current character to the stack
            stack.push(c);
            presentInStack[index] = true;
        }

        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) result.append(c);

        return result.toString();
    }
}
