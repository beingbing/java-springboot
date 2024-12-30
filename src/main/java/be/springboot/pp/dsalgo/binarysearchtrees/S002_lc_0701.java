package be.springboot.pp.dsalgo.binarysearchtrees;

public class S002_lc_0701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.data) root.left = insertIntoBST(root.left, val);
        else root.right = insertIntoBST(root.right, val); // val > root.val

        return root;
    }

    public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        if (root == null) return new TreeNode(val); // If the tree is empty, create a new node and return it
        TreeNode current = root;
        while (true) {
            if (val < current.data) { // If val < current value, move to the left
                if (current.left == null) {
                    current.left = new TreeNode(val);
                    break;
                }
                current = current.left;
            } else { // If val > current value, move to the right
                if (current.right == null) {
                    current.right = new TreeNode(val);
                    break;
                }
                current = current.right;
            }
        }

        return root;
    }
}
