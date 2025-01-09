package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class S003_gfg_topological_sort {
    public ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        return topoSort(adj.size(), adj);
    }

    public ArrayList<Integer> topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[V]; // Array to store indegree of each node
        int[] topoOrder = new int[V]; // Array to store the topological order
        Queue<Integer> queue = new LinkedList<>(); // Queue for nodes with 0 indegree

        for (int i = 0; i < V; i++) // Step 1: Compute indegree of each node
            for (int neighbor : adj.get(i)) indegree[neighbor]++;

        for (int i = 0; i < V; i++) // Step 2: Add all nodes with 0 indegree to the queue
            if (indegree[i] == 0) queue.add(i);

        // Step 3: Process nodes in the queue
        int index = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            topoOrder[index++] = current;

            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--; // Reduce indegree
                if (indegree[neighbor] == 0) queue.add(neighbor); // Add neighbor to queue if indegree becomes 0
            }
        }

        // If topoOrder doesn't contain all nodes, the graph has a cycle (invalid case)
        if (index != V) throw new IllegalStateException("Graph is not a DAG, topological sort is impossible");

//        return Arrays.stream(topoOrder)
//                .boxed()
//                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Integer> result = new ArrayList<Integer>(topoOrder.length);
        for (int i : topoOrder) result.add(i);
        return result;
    }
}
