package be.springboot.pp.dsalgo.binarysearchtrees;

public class S002_lc_0098 {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer low, Integer high) {
        if (node == null) return true; // An empty tree is a valid BST
        if ((low != null && node.data <= low) || (high != null && node.data >= high)) return false;
        return validate(node.left, low, node.data) && validate(node.right, node.data, high);
    }
}
