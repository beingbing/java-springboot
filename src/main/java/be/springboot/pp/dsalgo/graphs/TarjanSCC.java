package be.springboot.pp.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TarjanSCC {
    private final int vertices;
    private final List<List<Integer>> graph;
    private int time;
    private final int[] low;
    private final int[] disc;
    private final boolean[] onStack;
    private final Stack<Integer> stack;
    private final List<List<Integer>> sccs;

    public TarjanSCC(int vertices) {
        this.vertices = vertices;
        graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) graph.add(new ArrayList<>());
        time = 0;
        low = new int[vertices];
        disc = new int[vertices];
        Arrays.fill(disc, -1); // Unvisited
        onStack = new boolean[vertices];
        stack = new Stack<>();
        sccs = new ArrayList<>();
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }

    private void dfs(int u) {
        disc[u] = low[u] = time++;
        stack.push(u);
        onStack[u] = true;

        for (int v : graph.get(u)) {
            if (disc[v] == -1) { // If v is not visited
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) { // Back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // If u is a root of an SCC
        if (low[u] == disc[u]) {
            List<Integer> component = new ArrayList<>();
            int v;
            do {
                v = stack.pop();
                onStack[v] = false;
                component.add(v);
            } while (v != u);
            sccs.add(component);
        }
    }

    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < vertices; i++) {
            if (disc[i] == -1) {
                dfs(i);
            }
        }
        return sccs;
    }

    public static void main(String[] args) {
        TarjanSCC graph = new TarjanSCC(5);
        graph.addEdge(0, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        List<List<Integer>> sccs = graph.findSCCs();
        System.out.println("Strongly Connected Components:");
        for (List<Integer> component : sccs) {
            System.out.println(component);
        }
    }
}
