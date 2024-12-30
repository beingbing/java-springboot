package be.springboot.pp.dsalgo.narytrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S004_cf_1337c {
    private final List<List<Integer>> adjacencyList;
    private final int[] distanceFromRoot;
    private final int[] subtreeSize;

    public S004_cf_1337c(List<List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.distanceFromRoot = new int[adjacencyList.size()];
        this.subtreeSize = new int[adjacencyList.size()];
    }

    private void calDistanceAndSubtreeSize(int currentNode, int parentNode) {
        subtreeSize[currentNode] = 1; // Include the current node in its subtree size
        for (int neighbor : adjacencyList.get(currentNode)) {
            if (neighbor != parentNode) {
                distanceFromRoot[neighbor] = distanceFromRoot[currentNode] + 1; // children are farther away from root
                calDistanceAndSubtreeSize(neighbor, currentNode);
                subtreeSize[currentNode] += subtreeSize[neighbor];
            }
        }
    }

    public long calMaxHappiness(int n, int k) {
        List<Integer> priorities = new ArrayList<>();
        for (int i = 1; i <= n; i++) priorities.add(distanceFromRoot[i] - subtreeSize[i] + 1);

        priorities.sort(Collections.reverseOrder()); // Sort priorities in descending order and take the top k

        long maxHappiness = 0;
        for (int i = 0; i < k; i++) maxHappiness += priorities.get(i);
        return maxHappiness;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split("\\s+");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>()); // vertices are 1-indexed

        for (int i = 0; i < n - 1; i++) { // build adjacency-list
            input = br.readLine().trim().split("\\s+");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        S004_cf_1337c traverser = new S004_cf_1337c(tree);
        traverser.calDistanceAndSubtreeSize(1, -1);
        long happiness = traverser.calMaxHappiness(n, k);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(happiness + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}
