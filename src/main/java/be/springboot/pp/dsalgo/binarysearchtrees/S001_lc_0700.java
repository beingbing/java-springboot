package be.springboot.pp.dsalgo.binarysearchtrees;

public class S001_lc_0700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.data == val) return root;
        if (val < root.data) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }

    public TreeNode searchBSTIterative(TreeNode root, int val) {
        while (root != null)
            if (root.data == val) return root;
            else if (val < root.data) root = root.left;
            else root = root.right;
        return null; // Return null if the value is not found
    }
}
