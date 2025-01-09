package be.springboot.pp.dsalgo.graphs;

import java.util.List;

public class S002_he_connected_comps {

    public static int countConnectedComponents(int numberOfNodes, List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[numberOfNodes + 1]; // 1-based indexing
        int connectedComponentsCount = 0;
        for (int node = 1; node <= numberOfNodes; node++) {
            if (!visited[node]) {
                connectedComponentsCount++;
                performDFS(node, adjacencyList, visited);
            }
        }

        return connectedComponentsCount; // Return the count of components
    }

    private static void performDFS(int currentNode, List<List<Integer>> adjacencyList, boolean[] visited) {
        visited[currentNode] = true;

        for (int neighbor : adjacencyList.get(currentNode))
            if (!visited[neighbor]) performDFS(neighbor, adjacencyList, visited); // Recursive call
    }

}
