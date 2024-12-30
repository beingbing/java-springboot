package be.springboot.pp.dsalgo.binarysearchtrees;

public class S003_gfg_del_nodes_greater_k {
    public TreeNode deleteNode(TreeNode root, int k) {
        if (root == null) return null; // Base case: If the tree is empty

        // If the current node's value is greater than or equal to k
        if (root.data >= k) return deleteNode(root.left, k); // Discard this node and its right subtree

        root.left = deleteNode(root.left, k); // Otherwise, process the left and right subtrees recursively
        root.right = deleteNode(root.right, k);

        return root; // Return the current node
    }
}
