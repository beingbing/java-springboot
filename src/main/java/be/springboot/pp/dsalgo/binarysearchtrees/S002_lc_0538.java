package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.Stack;

public class S002_lc_0538 {
    private int sum = 0; // Global running sum

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null; // Base case: Empty tree
        convertBST(root.right); // Reverse Inorder Traversal: Process the right subtree
        sum += root.data; // Update the current node
        root.data = sum;
        convertBST(root.left); // Process the left subtree
        return root; // Return the transformed tree
    }

    public TreeNode convertBSTIterative(TreeNode root) {
        if (root == null) return null; // Base case: Empty tree

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int sum = 0;

        while (!stack.isEmpty() || current != null) { // Reverse Inorder Traversal
            while (current != null) { // Go as far right as possible
                stack.push(current);
                current = current.right;
            }

            current = stack.pop(); // Process the node
            sum += current.data;
            current.data = sum;

            current = current.left; // Move to the left subtree
        }

        return root; // Return the transformed tree
    }
}
