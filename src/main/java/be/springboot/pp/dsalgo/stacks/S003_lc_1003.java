package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S003_lc_1003 {
    public static boolean isValid(String input) {
        Stack<Character> stack = new Stack<>(); // Use a stack to keep track of characters in the string

        for (char currentChar : input.toCharArray()) // Iterate through each character in the input string
            if (currentChar == 'c') { // If the current character is 'c', check for the "abc" pattern
                if (stack.isEmpty() || stack.pop() != 'b') return false; // Pattern invalid if not preceded by 'b'
                if (stack.isEmpty() || stack.pop() != 'a') return false; // Pattern invalid if not preceded by 'a'
                // At this point, we've validated the "abc" pattern. No need to push 'c' onto the stack.
            } else stack.push(currentChar); // If the current character is not 'c', push it onto the stack

        return stack.isEmpty(); // If the stack is empty, all patterns were valid
    }

    public static void main(String[] args) {
        // Example test cases
        System.out.println(isValid("aabcbc"));            // Output: true
        System.out.println(isValid("abcabcababcc"));      // Output: true
        System.out.println(isValid("abccba"));            // Output: false
        System.out.println(isValid("cababc"));            // Output: false
        System.out.println(isValid("abc"));               // Output: true
        System.out.println(isValid("ab"));                // Output: false
    }
}
