package be.springboot.pp.dsalgo.graphs;

import java.util.Arrays;

public class AdjacencyMatrixGraph {
    private final int[][] matrix;

    public AdjacencyMatrixGraph(int n) {
        matrix = new int[n][n];
    }

    public void addEdge(int u, int v, boolean isDirected) {
        matrix[u][v] = 1;
        if (!isDirected) {
            matrix[v][u] = 1;
        }
    }

    public void addWeightedEdge(int u, int v, int weight, boolean isDirected) {
        matrix[u][v] = weight;
        if (!isDirected) {
            matrix[v][u] = weight;
        }
    }

    public int getDegree(int node) {
        int degree = 0;
        for (int j = 0; j < matrix.length; j++) {
            degree += matrix[node][j] > 0 ? 1 : 0;
        }
        return degree;
    }

    // Display the graph
    public void printGraph() {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
