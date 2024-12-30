package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class S001_lc_0173 {
    private final Stack<TreeNode> stack;

    public S001_lc_0173(TreeNode root) {
        stack = new Stack<>();
        pushLeftNodes(root);
    }

    private void pushLeftNodes(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode current = stack.pop(); // Get the top element
        if (current.right != null) pushLeftNodes(current.right); // Push all left nodes of the right subtree
        return current.data; // Return the value of the current node
    }

    public boolean hasNext() {
        return !stack.isEmpty(); // If stack is non-empty, we have elements left
    }
}

class S001_lc_0173_inorder {
    private final List<TreeNode> inorder;
    private int index;

    private void traverse(TreeNode node) {
        if (node == null) return;
        traverse(node.left);
        inorder.add(node);
        traverse(node.right);
    }

    public S001_lc_0173_inorder(TreeNode root) {
        inorder = new ArrayList<>();
        index = 0;
        traverse(root);
    }

    public int next() {
        return inorder.get(index++).data;
    }

    public boolean hasNext() {
        return index < inorder.size();
    }
}
