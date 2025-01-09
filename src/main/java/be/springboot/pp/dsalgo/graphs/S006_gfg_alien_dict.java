package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class S006_gfg_alien_dict {
    public String alienOrder(String[] words, int k) {
        List<Integer>[] graph = new ArrayList[k];
        int[] indegree = new int[k]; // To track in-degrees
        for (int i = 0; i < k; i++) graph[i] = new ArrayList<>();

        // Build edges by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len = Math.min(word1.length(), word2.length());

            boolean foundOrder = false;
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    int from = c1 - 'a';
                    int to = c2 - 'a';
                    graph[from].add(to);
                    indegree[to]++;
                    foundOrder = true;
                    break;
                }
            }

            // Check if prefix condition is violated
            if (!foundOrder && word1.length() > word2.length()) return "";
        }

        // Step 2: Topological Sort (Kahn's Algorithm)
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < k; i++)
            if (indegree[i] == 0) queue.offer(i);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.append((char) ('a' + node)); // Convert back to character
            for (int neighbor : graph[node])
                if (--indegree[neighbor] == 0) queue.offer(neighbor);
        }

        // Step 3: Check if all nodes were processed (Cycle Detection)
        return result.length() == k ? result.toString() : "";
    }
}
