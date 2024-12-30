package be.springboot.pp.dsalgo.stacks;

import java.util.Scanner;
import java.util.Stack;

public class S002_cf_797c {
    /**
     * Method to compute the lexicographically minimal string
     * @param inputString The input string
     * @return The lexicographically minimal string
     */
    private static String getLexicographicallyMinimalString(String inputString) {
        int[] frequency = new int[26]; // Frequency array to store character counts
        Stack<Character> stack = new Stack<>(); // Stack to hold characters temporarily
        StringBuilder result = new StringBuilder(); // Resultant string builder

        // Step 1: Count the frequency of each character in the input string
        for (char ch : inputString.toCharArray()) frequency[ch - 'a']++;

        int smallestAvailableIndex = 0; // Pointer to the smallest lexicographical character available

        // Step 2: Process each character in the input string
        for (char currentChar : inputString.toCharArray()) {
            // Move the smallestAvailableIndex to the first character with a non-zero frequency
            while (smallestAvailableIndex < 26 && frequency[smallestAvailableIndex] == 0) smallestAvailableIndex++;

            // Step 3: Pop characters from the stack if they are lexicographically smaller or equal
            while (!stack.isEmpty() && (stack.peek() - 'a') <= smallestAvailableIndex) result.append(stack.pop()); // Add the top character from the stack to the result

            // Step 4: Decrement the frequency of the current character and push it onto the stack
            frequency[currentChar - 'a']--;
            stack.push(currentChar);
        }

        // Step 5: Pop remaining characters from the stack and add them to the result
        while (!stack.isEmpty()) result.append(stack.pop());

        return result.toString();
    }
}
