package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class S003_hr_bracket_challenge {

    // Helper function to generate all valid sequences
    public static List<String> generateBalancedSequences(int n, int m) {
        List<String> result = new ArrayList<>();
        generateSequences(n, m, 0, 0, 0, 0, "", result);
        return result;
    }

    // Recursive function with backtracking to add balanced parentheses and braces
    private static void generateSequences(int n, int m, int openParen, int closeParen, int openBrace, int closeBrace, String sequence, List<String> result) {
        // Base case: if we have used up all pairs
        if (openParen == n && closeParen == n && openBrace == m && closeBrace == m) {
            result.add(sequence);
            return;
        }

        // Add an opening parenthesis if not exceeding `n`
        if (openParen < n)
            generateSequences(n, m, openParen + 1, closeParen, openBrace, closeBrace, sequence + "(", result);

        // Add a closing parenthesis if it would balance the sequence
        if (closeParen < openParen)
            generateSequences(n, m, openParen, closeParen + 1, openBrace, closeBrace, sequence + ")", result);

        // Add an opening brace if not exceeding `m`
        if (openBrace < m)
            generateSequences(n, m, openParen, closeParen, openBrace + 1, closeBrace, sequence + "{", result);

        // Add a closing brace if it would balance the sequence
        if (closeBrace < openBrace)
            generateSequences(n, m, openParen, closeParen, openBrace, closeBrace + 1, sequence + "}", result);
    }

    public static void main(String[] args) {
        int n = 1; // Number of pairs of parentheses
        int m = 2; // Number of pairs of braces
        List<String> sequences = generateBalancedSequences(n, m);
        for (String sequence : sequences) {
            System.out.println(sequence);
        }
    }
}
