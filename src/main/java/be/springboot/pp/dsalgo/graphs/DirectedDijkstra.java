package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DirectedDijkstra {
    private final int nodes;
    private final List<List<Node>> adjList;
    private final int[] dist;

    public DirectedDijkstra(int n) {
        this.nodes = n;
        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adjList.add(new ArrayList<>());
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
    }

    public void addDirectedEdge(int u, int v, int w) {
        adjList.get(u).add(new Node(v, w));
    }

    private int[] shortestPath(int start) {
        dist[start] = 0;

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        minHeap.offer(new Node(start, 0));

        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            int src = cur.value;

            for (Node neighbor : adjList.get(src)) {
                int dest = neighbor.value;
                int wt = neighbor.distance;

                if (dist[src] + wt < dist[dest]) {
                    dist[dest] = dist[src] + wt;
                    minHeap.offer(new Node(dest, dist[dest]));
                }
            }
        }

        return dist;
    }

    public int minTimeTaken(int start) {
        int[] dist = shortestPath(start);

        int ans = 0;
        for (int i = 1; i <= nodes; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, dist[i]);
        }

        return ans;
    }
}

class Node implements Comparable<Node> {
    int value, distance;

    public Node(int val, int dist) {
        this.value = val;
        this.distance = dist;
    }

    @Override
    public int compareTo(Node other) {
        return this.distance - other.distance;
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int nodes, int src) {
        DirectedDijkstra algo = new DirectedDijkstra(nodes);
        for (int[] time : times) algo.addDirectedEdge(time[0], time[1], time[2]);
        return algo.minTimeTaken(src);
    }
}