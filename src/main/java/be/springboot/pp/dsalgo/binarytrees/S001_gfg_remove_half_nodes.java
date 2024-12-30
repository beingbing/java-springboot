package be.springboot.pp.dsalgo.binarytrees;

public class S001_gfg_remove_half_nodes {
    public Node removeHalfNodes(Node root) {
        if (root == null) return null;
        root.left = removeHalfNodes(root.left);
        root.right = removeHalfNodes(root.right);

        // If the node is a half node, return its non-null child
        if (root.left == null && root.right != null) return root.right;
        if (root.right == null && root.left != null) return root.left;
        return root; // If the node is not a half node, return it
    }
}
