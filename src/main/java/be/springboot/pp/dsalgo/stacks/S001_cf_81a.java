package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S001_cf_81a {

    public static String removePairs(String s) {
        // Initialize a stack to store valid characters
        Stack<Character> stack = new Stack<>();

        // Process each character in the string
        for (char c : s.toCharArray()) {
            // If the stack is not empty and the top matches the current character, pop
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop(); // Remove the pair
            } else {
                stack.push(c); // Add the character to the stack
            }
        }

        // Construct the result string from the stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Example test cases
        System.out.println(removePairs("hhoowaaaareyyoouu")); // Output: "wre"
        System.out.println(removePairs("reallazy"));          // Output: "rezy"
        System.out.println(removePairs("abacabaabacabaa"));   // Output: "a"
    }
}
