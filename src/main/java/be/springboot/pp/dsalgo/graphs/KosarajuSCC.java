package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class KosarajuSCC {
    private final int vertices;
    private final List<List<Integer>> graph;
    private final List<List<Integer>> reversedGraph;

    public KosarajuSCC(int vertices) {
        this.vertices = vertices;
        graph = new ArrayList<>();
        reversedGraph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
            reversedGraph.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }

    private void reverseGraph() {
        for (int u = 0; u < vertices; u++)
            for (int v : graph.get(u))
                reversedGraph.get(v).add(u);
    }

    private void dfs1(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : graph.get(node))
            if (!visited[neighbor])
                dfs1(neighbor, visited, stack);
        stack.push(node);
    }

    private void dfs2(int node, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : reversedGraph.get(node))
            if (!visited[neighbor])
                dfs2(neighbor, visited, component);
    }

    public List<List<Integer>> findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        // Step 1: DFS on the original graph to fill the stack
        for (int i = 0; i < vertices; i++)
            if (!visited[i])
                dfs1(i, visited, stack);

        // Step 2: Reverse the graph
        reverseGraph();

        // Step 3: DFS on the reversed graph in the order of the stack
        Arrays.fill(visited, false);
        List<List<Integer>> sccs = new ArrayList<>();
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfs2(node, visited, component);
                sccs.add(component);
            }
        }

        return sccs;
    }

    public static void main(String[] args) {
        KosarajuSCC graph = new KosarajuSCC(5);
        graph.addEdge(0, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        List<List<Integer>> sccs = graph.findSCCs();
        System.out.println("Strongly Connected Components:");
        for (List<Integer> component : sccs) System.out.println(component);
    }
}
