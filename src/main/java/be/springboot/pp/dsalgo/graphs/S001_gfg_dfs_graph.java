package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

public class S001_gfg_dfs_graph {

    public ArrayList<Integer> depthFirstTraversal(int vertices, List<List<Integer>> adjacencyList) {
        ArrayList<Integer> traversalResult = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        performDFS(0, adjacencyList, visited, traversalResult);
        return traversalResult;
    }

    private void performDFS(int currentVertex, List<List<Integer>> adjacencyList, boolean[] visited, List<Integer> traversalResult) {
        visited[currentVertex] = true;
        traversalResult.add(currentVertex);
        for (int neighbor : adjacencyList.get(currentVertex))
            if (!visited[neighbor]) performDFS(neighbor, adjacencyList, visited, traversalResult);
    }
}
