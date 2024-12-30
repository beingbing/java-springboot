package be.springboot.pp.dsalgo.binarytrees;

public class S001_lc_0104 {
    public int maxDepth(Node root) {
        if (root == null) return 0; // Base case: empty tree
        int leftDepth = maxDepth(root.left); // Depth of left subtree
        int rightDepth = maxDepth(root.right); // Depth of right subtree
        return 1 + Math.max(leftDepth, rightDepth); // Add 1 for current node
    }
}
