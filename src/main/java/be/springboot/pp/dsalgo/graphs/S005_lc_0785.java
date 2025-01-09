package be.springboot.pp.dsalgo.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class S005_lc_0785 {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1); // Initialize all nodes as uncolored.

        for (int i = 0; i < n; i++) { // If the node is uncolored, check its component.
            if (color[i] == -1)
                if (!bfsCheck(graph, color, i)) return false; // If one component is not bipartite, the graph is not bipartite.
        }
        return true;
    }

    private boolean bfsCheck(int[][] graph, int[] color, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 0; // Start coloring the first node with color 0.

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph[node]) {
                if (color[neighbor] == -1) {
                    // If the neighbor is uncolored, assign it the opposite color.
                    color[neighbor] = 1 - color[node];
                    queue.offer(neighbor);
                } else if (color[neighbor] == color[node]) return false; // If the neighbor has the same color as the current node, the graph is not bipartite.
            }
        }
        return true;
    }
}
