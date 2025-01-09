package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class S005_lc_1203 {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < n; i++) // Step 1: Assign unique groups to items with no group (-1)
            if (group[i] == -1) group[i] = m++;

        List<Integer>[] groupGraph = new ArrayList[m];
        List<Integer>[] itemGraph = new ArrayList[n];
        int[] groupIndegree = new int[m];
        int[] itemIndegree = new int[n];

        for (int i = 0; i < m; i++) groupGraph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) itemGraph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) { // Step 3: Build graphs
            for (int before : beforeItems.get(i)) { // Add dependency between items
                itemGraph[before].add(i);
                itemIndegree[i]++;
                if (group[before] != group[i]) { // Add dependency between groups
                    groupGraph[group[before]].add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Step 4: Topological sort for groups and items
        List<Integer> groupOrder = topologicalSort(groupGraph, groupIndegree, m);
        if (groupOrder.isEmpty()) return new int[]{};

        List<Integer> itemOrder = topologicalSort(itemGraph, itemIndegree, n);
        if (itemOrder.isEmpty()) return new int[]{};

        // Step 5: Organize items based on group order
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder)
            groupToItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);

        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder)
            result.addAll(groupToItems.getOrDefault(g, new ArrayList<>()));

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Helper function for topological sort
    private List<Integer> topologicalSort(List<Integer>[] graph, int[] indegree, int size) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : graph[node]) {
                if (--indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return result.size() == size ? result : new ArrayList<>();
    }
}
