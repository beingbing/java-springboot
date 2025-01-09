package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdjacencyListGraph {
    private final int nodes;
    private final List<List<Integer>> adjacencyList;
    private final Map<Integer, List<Integer>> adjacencyMap;
    private final Map<Integer, List<int[]>> weightedAdjacencyMap;

    public AdjacencyListGraph() {
        this.nodes = 0;
        adjacencyList = new ArrayList<>();
        adjacencyMap = new HashMap<>();
        weightedAdjacencyMap = new HashMap<>();
    }

    public AdjacencyListGraph(int nodesCount) {
        this.nodes = nodesCount;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) adjacencyList.add(new ArrayList<>());
        adjacencyMap = new HashMap<>();
        weightedAdjacencyMap = new HashMap<>();
    }

    public void addListEdge(int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u); // For undirected graph
    }

    public void addEdge(int u, int v, boolean isDirected) {
        adjacencyMap.putIfAbsent(u, new ArrayList<>());
        adjacencyMap.get(u).add(v);

        if (!isDirected) {
            adjacencyMap.putIfAbsent(v, new ArrayList<>());
            adjacencyMap.get(v).add(u);
        }
    }

    public void addWeightedEdge(int u, int v, int weight, boolean isDirected) {
        weightedAdjacencyMap.putIfAbsent(u, new ArrayList<>());
        weightedAdjacencyMap.get(u).add(new int[]{v, weight});

        if (!isDirected) {
            weightedAdjacencyMap.putIfAbsent(v, new ArrayList<>());
            weightedAdjacencyMap.get(v).add(new int[]{u, weight});
        }
    }

    public int getDegree(int node) {
        return adjacencyMap.getOrDefault(node, Collections.emptyList()).size();
    }

    public void printGraph() {
        for (int node : adjacencyMap.keySet()) {
            System.out.println(node + " -> " + adjacencyMap.get(node));
        }
    }

    public void printWeightedGraph() {
        for (int node : weightedAdjacencyMap.keySet()) {
            System.out.print(node + " -> ");
            for (int[] edge : weightedAdjacencyMap.get(node)) {
                System.out.print(Arrays.toString(edge) + " ");
            }
            System.out.println();
        }
    }

    public void bfsOnAdjacencyList(int src) {
        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        visited[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public void bfsForShortestPath(int src) {
        int[] dist = new int[nodes];
        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();

        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity
        queue.add(src);
        dist[src] = 0; // Distance from src to itself is 0
        visited[src] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    dist[neighbor] = dist[node] + 1; // Update shortest distance
                    queue.add(neighbor);
                }
            }
        }

        // Print shortest distances from src
        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < nodes; i++) System.out.println("To node " + i + ": " + dist[i]);
    }

    public void bfsOnDisconnectedGraph() {
        boolean[] visited = new boolean[nodes];
        for (int i = 0; i < nodes; i++)
            if (!visited[i]) bfsOnAdjacencyListOfDisconnectedGraph(i, visited);
    }

    private void bfsOnAdjacencyListOfDisconnectedGraph(int src, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        visited[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    private void dfs(int node, boolean[] visited) {
        if (visited[node]) return;

        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    // Driver for DFS
    public void dfsTraversal(int startNode) {
        boolean[] visited = new boolean[nodes];
        dfs(startNode, visited);
    }

    // Driver for DFS in disconnected graph
    public void dfsDisconnected() {
        boolean[] visited = new boolean[nodes];
        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                dfs(i, visited);
            }
        }
    }
}
