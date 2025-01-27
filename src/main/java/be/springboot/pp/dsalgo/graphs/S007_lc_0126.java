package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class S007_lc_0126 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!wordSet.contains(endWord)) return result; // Edge case: endWord not in wordList

        // Step 1: BFS to find shortest paths
        Map<String, List<String>> parentMap = new HashMap<>(); // Tracks parent nodes
        bfs(beginWord, endWord, wordSet, parentMap);

        // Step 2: Backtrack to generate all paths
        List<String> path = new ArrayList<>();
        path.add(endWord); // Start from endWord and backtrack to beginWord
        backtrack(endWord, beginWord, parentMap, result, path);

        return result;
    }

    private void bfs(String beginWord, String endWord, Set<String> wordSet, Map<String, List<String>> parentMap) {
        Map<String, Integer> distance = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0); // Start distance as 0

        for (String word : wordSet) {
            parentMap.put(word, new ArrayList<>()); // Initialize parent map
        }

        boolean foundEnd = false;

        while (!queue.isEmpty() && !foundEnd) {
            int size = queue.size();
            Set<String> visitedThisLevel = new HashSet<>(); // Avoid revisiting nodes within the same level

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                int currentDistance = distance.get(currentWord);

                // Try all possible 1-letter transformations
                for (String neighbor : getNeighbors(currentWord, wordSet)) {
                    if (!distance.containsKey(neighbor)) { // First time visiting this neighbor
                        distance.put(neighbor, currentDistance + 1);
                        queue.offer(neighbor);
                        visitedThisLevel.add(neighbor);
                    }

                    // Add currentWord as a parent of neighbor if it's part of the shortest path
                    if (distance.get(neighbor) == currentDistance + 1) {
                        parentMap.get(neighbor).add(currentWord);
                    }

                    // Stop BFS early if endWord is reached
                    if (neighbor.equals(endWord)) {
                        foundEnd = true;
                    }
                }
            }

            // Remove visited nodes from wordSet to avoid revisiting
            wordSet.removeAll(visitedThisLevel);
        }
    }

    private void backtrack(String currentWord, String beginWord, Map<String, List<String>> parentMap,
                           List<List<String>> result, List<String> path) {
        if (currentWord.equals(beginWord)) {
            // Reverse path to get correct order from beginWord to endWord
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }

        for (String parent : parentMap.getOrDefault(currentWord, new ArrayList<>())) {
            path.add(parent);
            backtrack(parent, beginWord, parentMap, result, path);
            path.removeLast(); // Undo last addition (backtrack)
        }
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];

            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) continue;

                chars[i] = c;
                String newWord = new String(chars);

                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }

            chars[i] = originalChar; // Restore original word
        }
        return neighbors;
    }

    public static void main(String[] args) {
        S007_lc_0126 solver = new S007_lc_0126();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> result = solver.findLadders(beginWord, endWord, wordList);
        System.out.println(result); // Output: [[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
    }
}
