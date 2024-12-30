package be.springboot.pp.dsalgo.binarysearchtrees;

public class S002_lc_0099 {
    TreeNode first = null, second = null, prev = null;

    public void recoverTree(TreeNode root) {
        inOrderTraversal(root);
        // Swap the values of the two incorrect nodes
        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
    }

    private void inOrderTraversal(TreeNode root) {
        if (root == null) return;
        inOrderTraversal(root.left);

        // Check if current node is out of order
        if (prev != null && root.data < prev.data) {
            // Identify the two swapped nodes
            if (first == null) first = prev; // First occurrence
            second = root; // Second occurrence or adjacent nodes
        }

        prev = root; // store the currently processed node for pre-computation before processing the successor

        inOrderTraversal(root.right);
    }
}
