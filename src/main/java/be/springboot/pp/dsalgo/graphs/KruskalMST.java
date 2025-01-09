package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalMST {
    public int kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges); // Sort edges by weight

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int mstWeight = 0;
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : edges) {
            int root1 = dsu.find(edge.src);
            int root2 = dsu.find(edge.dest);

            // If adding this edge doesn't form a cycle
            if (root1 != root2) {
                mstEdges.add(edge);
                mstWeight += edge.weight;
                dsu.union(root1, root2);
            }

            // Stop when we have V-1 edges in the MST
            if (mstEdges.size() == n - 1) break;
        }

        // Print the MST edges
        System.out.println("Edges in the MST:");
        for (Edge edge : mstEdges) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int n = 4; // Number of vertices
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        );

        KruskalMST obj = new KruskalMST();
        int mstWeight = obj.kruskalMST(n, edges);
        System.out.println("Total weight of MST: " + mstWeight);
    }
}

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Compare edges by weight
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}