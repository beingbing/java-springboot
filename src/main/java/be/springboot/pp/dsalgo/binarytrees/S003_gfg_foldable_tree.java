package be.springboot.pp.dsalgo.binarytrees;

public class S003_gfg_foldable_tree {
    public boolean isFoldable(Node root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(Node left, Node right) {
        if (left == null && right == null) return true; // Both nodes are null, structure matches
        if (left == null || right == null) return false; // If only one of them is null, structure doesn't match
        return isMirror(left.left, right.right) && isMirror(left.right, right.left); // Recursively check their children for structural mirror
    }
}
