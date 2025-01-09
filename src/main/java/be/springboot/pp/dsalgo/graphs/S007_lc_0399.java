package be.springboot.pp.dsalgo.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class S007_lc_0399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) { // Step 1: Build the graph as an adjacency list
            String numerator = equations.get(i).get(0);
            String denominator = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(numerator, new HashMap<>());
            graph.putIfAbsent(denominator, new HashMap<>());

            graph.get(numerator).put(denominator, value);
            graph.get(denominator).put(numerator, 1.0 / value);
        }

        double[] results = new double[queries.size()]; // Step 2: Evaluate each query

        for (int i = 0; i < queries.size(); i++) {
            String numerator = queries.get(i).get(0);
            String denominator = queries.get(i).get(1);

            if (!graph.containsKey(numerator) || !graph.containsKey(denominator)) results[i] = -1.0; // One or both variables are not in the graph
            else if (numerator.equals(denominator)) results[i] = 1.0; // Division of a variable by itself
            else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(graph, numerator, denominator, 1.0, visited);
            }
        }

        return results;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String current, String target, double product, Set<String> visited) {
        if (current.equals(target)) return product;

        visited.add(current);

        for (Map.Entry<String, Double> neighbor : graph.get(current).entrySet()) {
            String nextNode = neighbor.getKey();
            double weight = neighbor.getValue();

            if (!visited.contains(nextNode)) {
                double result = dfs(graph, nextNode, target, product * weight, visited);
                if (result != -1.0) return result;
            }
        }

        return -1.0; // No path found
    }
}
