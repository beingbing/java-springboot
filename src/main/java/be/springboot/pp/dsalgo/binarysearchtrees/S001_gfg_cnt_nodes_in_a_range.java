package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.Stack;

public class S001_gfg_cnt_nodes_in_a_range {
    public int getCount(TreeNode root, int l, int h) {
        if (root == null) return 0; // Base case: no nodes
        if (root.data >= l && root.data <= h) return 1 + getCount(root.left, l, h) + getCount(root.right, l, h); // If current node is within range
        if (root.data < l) return getCount(root.right, l, h); // If current node is less than l, move to the right subtree
        return getCount(root.left, l, h); // If current node is greater than h, move to the left subtree
    }

    public int countNodesInRange(TreeNode root, int l, int h) {
        if (root == null) return 0;

        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.data >= l && current.data <= h) count++; // If current node is within range
            if (current.left != null && current.data > l) stack.push(current.left); // Push left child if it can contain nodes within range
            if (current.right != null && current.data < h) stack.push(current.right); // Push right child if it can contain nodes within range
        }

        return count;
    }
}
