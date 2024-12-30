package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
}

class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        current.isWord = true;
    }
}

public class S003_gfg_word_boggle {
    private static final int[] ROW_DIRS = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static final int[] COL_DIRS = {-1, 0, 1, 1, 1, 0, -1, -1};

    public String[] wordBoggle(char[][] board, String[] dictionary) {
        // Step 1: Build Trie for the dictionary words
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }

        Set<String> resultSet = new HashSet<>();
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Step 2: Perform DFS from each cell in the board
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                dfs(board, row, col, trie.root, "", visited, resultSet);

        // Step 3: Collect results and sort lexicographically
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    private void dfs(char[][] board, int row, int col, TrieNode node, String path, boolean[][] visited, Set<String> resultSet) {
        // Boundary check and cell visit status
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]) return;

        char currentChar = board[row][col];
        if (!node.children.containsKey(currentChar)) return;

        node = node.children.get(currentChar);
        path += currentChar;

        if (node.isWord) resultSet.add(path);

        // Mark cell as visited
        visited[row][col] = true;

        // Explore all 8 directions
        for (int d = 0; d < 8; d++) {
            int newRow = row + ROW_DIRS[d];
            int newCol = col + COL_DIRS[d];
            dfs(board, newRow, newCol, node, path, visited, resultSet);
        }

        // Backtrack: Unmark the cell as visited
        visited[row][col] = false;
    }
}
