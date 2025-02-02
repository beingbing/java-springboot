package be.springboot.pp.designpattern.behavioral.iterator.bst;

import be.springboot.pp.designpattern.behavioral.iterator.Iterator;

import java.util.Stack;

public class BstIterator implements Iterator {
    private final Stack<TreeNode> stack = new Stack<>();

    public BstIterator(TreeNode root) {
        pushLeft(root);
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left; // Push left subtree nodes onto the stack
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public int next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }

        TreeNode node = stack.pop();
        pushLeft(node.right); // Move to the right subtree
        return node.value;
    }
}
