package be.springboot.pp.dsalgo.binarytrees;

public class S003_lc_0124 {
    private int maxPathSumGlobal = Integer.MIN_VALUE; // Tracks the maximum path sum globally

    public int maxPathSum(Node root) {
        calculateMaxPathSum(root);
        return maxPathSumGlobal;
    }

    private int calculateMaxPathSum(Node node) {
        if (node == null) return 0;
        // For each node, calculate the maximum contribution it can make to its parent
        int leftSum = Math.max(0, calculateMaxPathSum(node.left)); // Ignore negative sums
        int rightSum = Math.max(0, calculateMaxPathSum(node.right)); // Ignore negative sums
        maxPathSumGlobal = Math.max(maxPathSumGlobal, node.data + leftSum + rightSum); // Update global maximum path sum considering paths through the current node
        return node.data + Math.max(leftSum, rightSum);
    }
}
