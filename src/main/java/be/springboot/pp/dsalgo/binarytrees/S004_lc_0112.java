package be.springboot.pp.dsalgo.binarytrees;

public class S004_lc_0112 {
    public boolean hasPathSum(Node root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum == root.data; // Check if the current node is a leaf
        int remainingSum = targetSum - root.data;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
}
