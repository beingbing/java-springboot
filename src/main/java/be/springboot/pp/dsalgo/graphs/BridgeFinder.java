package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgeFinder {
    private final int nodes;
    private final List<List<Integer>> adjList;
    private int time;
    private final int[] disc, low;
    private final boolean[] visited;
    private List<List<Integer>> bridges;

    public BridgeFinder(int n) {
        this.nodes = n;
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        disc = new int[n];
        Arrays.fill(disc, -1);
        low = new int[n];
        visited = new boolean[n];
        time = 0;
        bridges = new ArrayList<>();
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public List<List<Integer>> findBridges() {
        bridges = new ArrayList<>();
        for (int i = 0; i < nodes; i++)
            if (!visited[i])
                dfs(i, -1);
        return bridges;
    }

    private void dfs(int cur, int parent) {
        visited[cur] = true;
        disc[cur] = low[cur] = time++;

        for (int neighbor : adjList.get(cur)) {
            if (neighbor == parent) continue;
            if (visited[neighbor]) low[cur] = Math.min(low[cur], disc[neighbor]); // Back edge
            else {
                dfs(neighbor, cur);
                low[cur] = Math.min(low[cur], low[neighbor]);
                if (low[neighbor] > disc[cur]) bridges.add(Arrays.asList(cur, neighbor));
            }
        }
    }

    public static void main(String[] args) {
        BridgeFinder finder = new BridgeFinder(4);

        finder.addEdge(0, 1);
        finder.addEdge(1, 2);
        finder.addEdge(0, 2);
        finder.addEdge(1, 3);

        List<List<Integer>> answer = finder.findBridges();
        System.out.println("Bridges: " + answer);
    }
}
