package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

public class S001_gfg_detect_cycle_in_ug {
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int nodesCount = Integer.MIN_VALUE;
        for (List<Integer> list : adj)
            for (int ele : list)
                nodesCount = Math.max(nodesCount, ele);
        return isCycle(nodesCount+1, adj);
    }

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) // Check for cycles in all components of the graph
            if (!visited[i])
                if (dfsHasCycle(i, -1, visited, adj)) return true;

        return false;
    }

    private boolean dfsHasCycle(int node, int parent, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;

        for (int neighbor : adj.get(node))
            if (!visited[neighbor]) {
                if (dfsHasCycle(neighbor, node, visited, adj)) return true;
            } else if (neighbor != parent) return true; // A visited node not equal to parent means a cycle

        return false;
    }
}
