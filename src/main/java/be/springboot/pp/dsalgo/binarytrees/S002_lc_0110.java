package be.springboot.pp.dsalgo.binarytrees;

public class S002_lc_0110 {
    public boolean isBalanced(Node root) {
        return checkHeight(root) != -1; // Helper function returns -1 if unbalanced, otherwise the height of the subtree
    }

    private int checkHeight(Node node) {
        if (node == null) return 0;
        int leftHeight = checkHeight(node.left);
        int rightHeight = checkHeight(node.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1; // If any subtree is unbalanced, propagate -1 upwards
        return 1 + Math.max(leftHeight, rightHeight); // Return the height of the current node
    }
}
