package be.springboot.pp.dsalgo.binarytrees;

public class S004_lc_0814 {
    public Node pruneTree(Node root) {
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // If the current node's value is 0 and both subtrees are null, prune this node
        if (root.data == 0 && root.left == null && root.right == null) return null;
        return root; // Otherwise, return the current node
    }
}
