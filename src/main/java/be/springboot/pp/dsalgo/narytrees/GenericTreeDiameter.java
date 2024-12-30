package be.springboot.pp.dsalgo.narytrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GenericTreeDiameter {
    private final List<List<Integer>> adjacencyList;
    private int farthestNode;
    private int maxDistance;

    public GenericTreeDiameter(List<List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    private void dfs(int node, int parent, int distance) {
        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (int neighbor : adjacencyList.get(node)) {
            if (neighbor != parent) { // Avoid revisiting the parent
                dfs(neighbor, node, distance + 1);
            }
        }
    }

    public int findDiameter() {
        // First DFS to find the farthest node from any node (say node 1)
        maxDistance = -1;
        dfs(1, -1, 0);

        // Second DFS from the farthest node found
        maxDistance = -1;
        dfs(farthestNode, -1, 0);

        return maxDistance;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        GenericTreeDiameter traverser = new GenericTreeDiameter(tree);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(traverser.findDiameter() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
