package be.springboot.pp.dsalgo.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class S001_lc_1302 {
    private int maxDepth = 0;
    private int deepestSum = 0;

    // BFS
    public int deepestLeavesSumBFS(Node root) {
        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;

        while (!queue.isEmpty()) {
            sum = 0; // Reset sum for the current level
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                sum += node.data; // Add all nodes at the current level

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return sum; // The sum of the last level
    }

    // DFS
    public int deepestLeavesSumDFS(Node root) {
        dfs(root, 0);
        return deepestSum;
    }

    private void dfs(Node node, int depth) {
        if (node == null) return;

        // If it's a leaf node
        if (node.left == null && node.right == null) {
            if (depth > maxDepth) {
                maxDepth = depth;
                deepestSum = node.data; // New max depth, reset sum
            } else if (depth == maxDepth) {
                deepestSum += node.data; // Same max depth, add to sum
            }
        }

        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}
