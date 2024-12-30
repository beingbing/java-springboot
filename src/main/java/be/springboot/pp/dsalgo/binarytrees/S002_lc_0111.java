package be.springboot.pp.dsalgo.binarytrees;

public class S002_lc_0111 {
    public int minDepth(Node root) {
        if (root == null) return 0; // Base case: Empty tree

        // If one child is null, return depth of the other child + 1
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);

        // Both children are non-null, return the minimum depth of left and right
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}
