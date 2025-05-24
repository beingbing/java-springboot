package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {
    private final int vertices;
    private final List<List<Integer>> graph;
    private int time;
    private final int[] disc, low, parent;
    private final boolean[] visited, isArticulation;

    public ArticulationPoints(int vertices) {
        this.vertices = vertices;
        graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) graph.add(new ArrayList<>());
        disc = new int[vertices];
        low = new int[vertices];
        parent = new int[vertices];
        visited = new boolean[vertices];
        isArticulation = new boolean[vertices];
        Arrays.fill(disc, -1); // Unvisited
        Arrays.fill(parent, -1); // No parent initially
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u); // Undirected graph
    }

    private void dfs(int u) {
        visited[u] = true;
        disc[u] = low[u] = ++time; // Initialize discovery and low values
        int children = 0; // Count of children in DFS tree

        for (int v : graph.get(u)) {
            if (!visited[v]) { // If v is not visited
                parent[v] = u;
                children++;
                dfs(v);

                // Update low value of u for the subtree
                low[u] = Math.min(low[u], low[v]);

                // Check if u is an articulation point
                if (parent[u] == -1 && children > 1) // Root node
                    isArticulation[u] = true;
                if (parent[u] != -1 && low[v] >= disc[u]) // Non-root node
                    isArticulation[u] = true;
            } else if (v != parent[u]) low[u] = Math.min(low[u], disc[v]); // Back edge
        }
    }

    public List<Integer> findArticulationPoints() {
        for (int i = 0; i < vertices; i++) if (!visited[i]) dfs(i);
        List<Integer> articulationPoints = new ArrayList<>();
        for (int i = 0; i < vertices; i++) if (isArticulation[i]) articulationPoints.add(i);
        return articulationPoints;
    }

    public static void main(String[] args) {
        ArticulationPoints graph = new ArticulationPoints(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        List<Integer> articulationPoints = graph.findArticulationPoints();
        System.out.println("Articulation Points: " + articulationPoints);
    }
}
