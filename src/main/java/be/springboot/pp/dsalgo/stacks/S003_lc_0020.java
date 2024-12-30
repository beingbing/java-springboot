package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

public class S003_lc_0020 {
    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) return false; // Edge case: If string length is odd, it cannot be valid

        // Map to store the corresponding pairs
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put(')', '(');
        bracketPairs.put('}', '{');
        bracketPairs.put(']', '[');

        // Stack to track unmatched opening brackets
        Stack<Character> stack = new Stack<>();

        // Traverse each character in the string
        for (char c : s.toCharArray()) {
            // If it's a closing bracket
            if (bracketPairs.containsKey(c)) {
                // Pop the top of the stack or use a dummy value if stack is empty
                char top = stack.isEmpty() ? '#' : stack.pop();

                // Check if the popped value matches the expected opening bracket
                if (top != bracketPairs.get(c)) {
                    return false; // Mismatch found
                }
            } else {
                // If it's an opening bracket, push it onto the stack
                stack.push(c);
            }
        }

        // If the stack is empty, all brackets were matched
        return stack.isEmpty();
    }
}
