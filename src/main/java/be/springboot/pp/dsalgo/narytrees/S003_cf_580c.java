package be.springboot.pp.dsalgo.narytrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class S003_cf_580c {
    private final List<List<Integer>> adjacencyList;
    private int count = 0;

    public S003_cf_580c(List<List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public void traverse(int currentNode, int parentNode, int[] blockers, int consecutiveBlockers, int maxBlockers) {
        if (blockers[currentNode] == 1) consecutiveBlockers++;
        else consecutiveBlockers = 0;

        if (consecutiveBlockers > maxBlockers) return;

        boolean isLeaf = true;
        for (int neighbor : adjacencyList.get(currentNode)) {
            if (neighbor != parentNode) { // Skip the parent node
                isLeaf = false;
                traverse(neighbor, currentNode, blockers, consecutiveBlockers, maxBlockers);
            }
        }

        if (isLeaf) count++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split("\\s+");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] blockers = new int[n + 1]; // blockers are 1-indexed
        input = br.readLine().trim().split("\\s+");
        for (int i = 1; i <= n; i++) blockers[i] = Integer.parseInt(input[i-1]);

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>()); // vertices are 1-indexed

        for (int i = 0; i < n - 1; i++) { // build adjacency-list
            input = br.readLine().trim().split("\\s+");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        S003_cf_580c traverser = new S003_cf_580c(tree);
        traverser.count = 0;
        traverser.traverse(1, -1, blockers, 0, m);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(traverser.count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
