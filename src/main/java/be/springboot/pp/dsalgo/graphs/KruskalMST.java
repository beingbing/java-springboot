package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalMST {
    public int kruskal(int n, List<KruskalEdge> edges) {
        Collections.sort(edges); // Sort edges by weight

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int mstWeight = 0;
        List<KruskalEdge> mstEdges = new ArrayList<>();

        for (KruskalEdge edge : edges) {
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
        for (KruskalEdge edge : mstEdges) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int n = 4; // Number of vertices
        List<KruskalEdge> edges = Arrays.asList(
                new KruskalEdge(0, 1, 10),
                new KruskalEdge(0, 2, 6),
                new KruskalEdge(0, 3, 5),
                new KruskalEdge(1, 3, 15),
                new KruskalEdge(2, 3, 4)
        );

        KruskalMST obj = new KruskalMST();
        int mstWeight = obj.kruskal(n, edges);
        System.out.println("Total weight of MST: " + mstWeight);
    }
}

class KruskalEdge implements Comparable<KruskalEdge> {
    int src, dest, weight;

    public KruskalEdge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Compare edges by weight
    public int compareTo(KruskalEdge other) {
        return this.weight - other.weight;
    }
}
