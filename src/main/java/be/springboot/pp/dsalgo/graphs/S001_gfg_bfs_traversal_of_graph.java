package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class S001_gfg_bfs_traversal_of_graph {
    public ArrayList<Integer> bfsOfGraph(int nodesCount, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[nodesCount];
        Queue<Integer> queue = new LinkedList<>();
        visited[0] = true; // traversal starts from node 0 in this problem
        queue.add(0);
        ArrayList<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }
}
