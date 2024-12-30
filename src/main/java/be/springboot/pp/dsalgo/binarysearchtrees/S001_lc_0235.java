package be.springboot.pp.dsalgo.binarysearchtrees;

public class S001_lc_0235 {
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.data < root.data && q.data < root.data) root = root.left; // Both nodes are in the left subtree
            else if (p.data > root.data && q.data > root.data) root = root.right; // Both nodes are in the right subtree
            else return root; // Current node is the LCA
        }
        return null; // This line is unreachable because p and q are guaranteed to exist
    }

    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.data < root.data && q.data < root.data) return lowestCommonAncestorRecursive(root.left, p, q); // Both nodes are in the left subtree
        else if (p.data > root.data && q.data > root.data) return lowestCommonAncestorRecursive(root.right, p, q); // Both nodes are in the right subtree
        else return root; // Current node is the LCA
    }
}
