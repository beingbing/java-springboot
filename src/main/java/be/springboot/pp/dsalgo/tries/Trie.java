package be.springboot.pp.dsalgo.tries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.prefixCount++;
        }
        node.isEndOfWord = true; // Mark the end of the string
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) return false; // If character not found, word doesn't exist
            node = node.children.get(c);
        }
        return node.isEndOfWord; // Return true if it's the end of a word
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) return false; // If character not found, prefix doesn't exist
            current = current.children.get(c);
        }
        return true; // Return true if prefix exists
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    // doesn't contain prefixCount readjustment. To do that instead of checking
    // children hash emptiness, reduce prefixCount and if it gets to 0 then remove
    // the current node
    private boolean delete(TrieNode node, String word, int depth) {
        if (depth == word.length()) { // If end of the word is reached
            if (!node.isEndOfWord) return false; // Word not present
            node.isEndOfWord = false; // Unmark the end of the string

            return node.children.isEmpty(); // Check if node can be deleted
        }

        char c = word.charAt(depth);
        TrieNode next = node.children.get(c);
        if (next == null) return false; // Word does not exist

        boolean canDeleteChild = delete(next, word, depth + 1);

        if (canDeleteChild) {
            node.children.remove(c);
            return node.children.isEmpty() && !node.isEndOfWord;
        }

        return false;
    }

    public boolean remove(String word) { // delete iteratively
        Stack<TrieNode> stack = new Stack<>(); // Track nodes visited for backtracking
        Stack<Character> path = new Stack<>();

        TrieNode current = root;

        // Traverse the Trie to find the word
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) return false; // Word not found
            stack.push(current);
            path.push(c);
            current = current.children.get(c);
        }

        if (!current.isEndOfWord) return false; // Word not found as complete word

        // Unmark the end of the word
        current.isEndOfWord = false;

        // Remove unnecessary nodes from the Trie
        while (!stack.isEmpty() && current.children.isEmpty() && !current.isEndOfWord) {
            TrieNode parent = stack.pop();
            char c = path.pop();
            parent.children.remove(c);
            current = parent;
        }

        return true; // Word successfully deleted
    }

    // approach 2: keep a prefix-count for each node
    public int countPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return 0; // Prefix does not exist
            node = node.children.get(c);
        }
        return node.prefixCount;
    }

    // approach 1: calculate strings count which
    // are ending post prefix string is traversed.
//    private int countStrings(TrieNode node) {
//        if (node == null) return 0;
//
//        int count = node.isString ? 1 : 0;
//        for (TrieNode child : node.children) {
//            count += countStrings(child);
//        }
//
//        return count;
//    }

    public void display() {
        List<String> result = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        displayHelper(root, currentWord, result);

        for (String word : result) System.out.println(word);
    }

    // Helper function to display Trie content
    private void displayHelper(TrieNode node, StringBuilder currentWord, List<String> result) {
        if (node.isEndOfWord) result.add(currentWord.toString());

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            currentWord.append(entry.getKey());
            displayHelper(entry.getValue(), currentWord, result);
            currentWord.deleteCharAt(currentWord.length() - 1); // Backtrack
        }
    }

    String findUniquePrefix(String word) {
        TrieNode current = root;
        StringBuilder prefix = new StringBuilder();
        for (char c : word.toCharArray()) {
            prefix.append(c);
            current = current.children.get(c);
            if (current.prefixCount == 1) break; // Found unique prefix
        }
        return prefix.toString();
    }

    public String findShortestRoot(String word) {
        TrieNode current = root;
        StringBuilder prefix = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) break; // No matching prefix
            prefix.append(c);
            current = current.children.get(c);
            if (current.isEndOfWord) return prefix.toString(); // Found the shortest root
        }
        return word; // No root matches, return the original word
    }
}
