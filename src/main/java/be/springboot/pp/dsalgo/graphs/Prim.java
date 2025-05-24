package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

    public static int primMST(List<List<EdgeEnd>> adjList, int n) {
        boolean[] inMST = new boolean[n];
        PriorityQueue<EdgeEnd> pq = new PriorityQueue<>();
        int totalWeight = 0;

        pq.offer(new EdgeEnd(0, 0));

        while (!pq.isEmpty()) {
            EdgeEnd edge = pq.poll();
            int u = edge.dest;
            int weight = edge.weight;

            if (inMST[u]) continue; // Skip if already in MST

            inMST[u] = true;
            totalWeight += weight;

            for (EdgeEnd neighbor : adjList.get(u))
                if (!inMST[neighbor.dest])
                    pq.offer(new EdgeEnd(neighbor.dest, neighbor.weight));
        }

        return totalWeight;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<EdgeEnd>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Undirected weighted graph
        graph.get(0).add(new EdgeEnd(1, 2));
        graph.get(1).add(new EdgeEnd(0, 2));

        graph.get(0).add(new EdgeEnd(3, 6));
        graph.get(3).add(new EdgeEnd(0, 6));

        graph.get(1).add(new EdgeEnd(2, 3));
        graph.get(2).add(new EdgeEnd(1, 3));

        graph.get(1).add(new EdgeEnd(3, 8));
        graph.get(3).add(new EdgeEnd(1, 8));

        graph.get(1).add(new EdgeEnd(4, 5));
        graph.get(4).add(new EdgeEnd(1, 5));

        graph.get(2).add(new EdgeEnd(4, 7));
        graph.get(4).add(new EdgeEnd(2, 7));

        int totalWeight = primMST(graph, n);
        System.out.println("Total weight of MST: " + totalWeight);
    }
}

class EdgeEnd implements Comparable<EdgeEnd> {
    int dest, weight;

    EdgeEnd(int vertex, int weight) {
        this.dest = vertex;
        this.weight = weight;
    }

    public int compareTo(EdgeEnd other) {
        return this.weight - other.weight;
    }
}
