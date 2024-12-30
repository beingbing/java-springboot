package be.springboot.pp.dsalgo.binarytrees;

public class S003_lc_1448 {
    public int goodNodes(Node root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(Node node, int maxSoFar) {
        if (node == null) return 0; // Base case: no node to process

        int count = 0;
        if (node.data >= maxSoFar) count = 1; // Current node is "good"

        // Recur for left and right subtrees with updated max
        count += dfs(node.left, Math.max(maxSoFar, node.data));
        count += dfs(node.right, Math.max(maxSoFar, node.data));

        return count;
    }
}
