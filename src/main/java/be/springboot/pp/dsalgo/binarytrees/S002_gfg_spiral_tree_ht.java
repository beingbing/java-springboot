package be.springboot.pp.dsalgo.binarytrees;

public class S002_gfg_spiral_tree_ht {
    public int findHeight(Node root) {
        if (root == null) return 0;
        if ((root.left != null)
                && (root.left.right == root)
                && (root.right != null)
                && (root.right.left == root)) return 1; // extra unnecessary condition
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
