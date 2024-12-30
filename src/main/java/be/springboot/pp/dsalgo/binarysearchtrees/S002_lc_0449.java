package be.springboot.pp.dsalgo.binarysearchtrees;

public class S002_lc_0449 {
    private final String DELIMITER = ",";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorderSerialize(root, sb);
        return sb.toString();
    }

    private void preorderSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.data).append(DELIMITER); // Add current node value
        preorderSerialize(root.left, sb); // Recurse on left child
        preorderSerialize(root.right, sb); // Recurse on right child
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] nodes = data.split(DELIMITER); // Split string into nodes
        int[] index = {0}; // Use an array to keep index reference
        return preorderDeserialize(nodes, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode preorderDeserialize(String[] nodes, int[] index, int min, int max) {
        if (index[0] >= nodes.length) return null;

        int val = Integer.parseInt(nodes[index[0]]);
        if (val < min || val > max) return null; // Invalid for BST

        index[0]++; // Move to next node
        TreeNode root = new TreeNode(val); // Create the current node
        root.left = preorderDeserialize(nodes, index, min, val); // Build left subtree
        root.right = preorderDeserialize(nodes, index, val, max); // Build right subtree
        return root;
    }
}
