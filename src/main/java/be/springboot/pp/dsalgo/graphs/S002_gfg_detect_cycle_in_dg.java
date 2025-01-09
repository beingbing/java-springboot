package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;

public class S002_gfg_detect_cycle_in_dg {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        // Check for cycles in all components of the graph
        for (int i = 0; i < V; i++)
            if (!visited[i] && dfsHasCycle(i, visited, pathVisited, adj)) return true;

        return false;
    }

    private boolean dfsHasCycle(int node, boolean[] visited, boolean[] pathVisited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true; // Mark the node as visited
        pathVisited[node] = true; // Mark the node as part of the current DFS path

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfsHasCycle(neighbor, visited, pathVisited, adj)) return true;
            } else if (pathVisited[neighbor]) return true; // A back edge is found
        }

        // Backtrack: remove the node from the current DFS path
        pathVisited[node] = false;
        return false;
    }
}
