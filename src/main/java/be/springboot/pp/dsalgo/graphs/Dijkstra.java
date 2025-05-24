package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static int[] dijkstra(int nodes, List<List<DijkstraEdge>> graph, int source) {
        int[] dist = new int[nodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>();
        pq.offer(new DijkstraNode(source, 0));

        while (!pq.isEmpty()) {
            DijkstraNode current = pq.poll();
            int u = current.vertex;

            for (DijkstraEdge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new DijkstraNode(v, dist[v]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<DijkstraEdge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Example Graph
        graph.get(0).add(new DijkstraEdge(1, 10));
        graph.get(0).add(new DijkstraEdge(4, 5));
        graph.get(1).add(new DijkstraEdge(2, 1));
        graph.get(1).add(new DijkstraEdge(4, 2));
        graph.get(2).add(new DijkstraEdge(3, 4));
        graph.get(3).add(new DijkstraEdge(2, 6));
        graph.get(3).add(new DijkstraEdge(0, 7));
        graph.get(4).add(new DijkstraEdge(1, 3));
        graph.get(4).add(new DijkstraEdge(2, 9));
        graph.get(4).add(new DijkstraEdge(3, 2));

        int source = 0;
        int[] distances = dijkstra(n, graph, source);

        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("To node " + i + " = " + distances[i]);
        }
    }
}

class DijkstraEdge {
    int to, weight;
    DijkstraEdge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class DijkstraNode implements Comparable<DijkstraNode> {
    int vertex, dist;
    DijkstraNode(int vertex, int dist) {
        this.vertex = vertex;
        this.dist = dist;
    }

    public int compareTo(DijkstraNode other) {
        return this.dist - other.dist;
    }
}