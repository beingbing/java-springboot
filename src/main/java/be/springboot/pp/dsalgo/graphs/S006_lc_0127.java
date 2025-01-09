package be.springboot.pp.dsalgo.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class S006_lc_0127 {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0; // endWord not in wordList

        // Queue for BFS
        Queue<WordDistancePair<String, Integer>> queue = new LinkedList<>();
        queue.add(new WordDistancePair<>(beginWord, 1));

        // BFS
        while (!queue.isEmpty()) {
            WordDistancePair<String, Integer> current = queue.poll();
            String word = current.getKey();
            int level = current.getValue();

            // Try all possible transformations
            for (int i = 0; i < word.length(); i++) {
                char[] wordArray = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String transformed = new String(wordArray);

                    if (transformed.equals(endWord)) {
                        return level + 1;
                    }

                    if (wordSet.contains(transformed)) {
                        queue.add(new WordDistancePair<>(transformed, level + 1));
                        wordSet.remove(transformed); // Mark as visited
                    }
                }
            }
        }

        return 0; // No valid transformation sequence
    }
}

class WordDistancePair<K, V> {
    private final K key;
    private final V value;

    public WordDistancePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}