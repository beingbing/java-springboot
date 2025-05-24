package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimAlgorithm {

    public static List<PrimEdge> primMST(Graph graph) {
        int vertices = graph.vertices;

        // Priority queue to pick the smallest edge
        PriorityQueue<PrimEdge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        boolean[] visited = new boolean[vertices];

        List<PrimEdge> mst = new ArrayList<>();
        minHeap.add(new PrimEdge(-1, 0, 0)); // Start with vertex 0, weight 0

        while (!minHeap.isEmpty()) {
            PrimEdge currentEdge = minHeap.poll();

            if (visited[currentEdge.dest]) continue;

            visited[currentEdge.dest] = true;

            // If the edge is valid, add it to MST
            if (currentEdge.src != -1) mst.add(currentEdge);

            // Add all edges from the current vertex to the minHeap
            for (PrimEdge edge : graph.adjacencyList.get(currentEdge.dest))
                if (!visited[edge.dest]) minHeap.add(edge);
        }

        return mst;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        List<PrimEdge> mst = primMST(graph);
        System.out.println("Minimum Spanning Tree:");
        for (PrimEdge edge : mst)
            System.out.println("Edge: " + edge.src + " - " + edge.dest + ", Weight: " + edge.weight);
    }
}

class PrimEdge {
    int src, dest, weight;

    PrimEdge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int vertices;
    List<List<PrimEdge>> adjacencyList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++)
            adjacencyList.add(new ArrayList<>());
    }

    void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new PrimEdge(src, dest, weight));
        adjacencyList.get(dest).add(new PrimEdge(dest, src, weight)); // Undirected graph
    }
}