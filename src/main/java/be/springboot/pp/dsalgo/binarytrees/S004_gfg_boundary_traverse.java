package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class S004_gfg_boundary_traverse {
    public ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        if (!isLeaf(root)) result.add(root.data);
        addLeftBoundary(root.left, result); // Add the left boundary (excluding leaves)
        addLeaves(root, result); // Add all leaf nodes
        List<Integer> rightBoundary = new ArrayList<>(); // Add the right boundary in reverse order (excluding leaves)
        addRightBoundary(root.right, rightBoundary);
        Collections.reverse(rightBoundary);
        result.addAll(rightBoundary);
        return result;
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private void addLeftBoundary(Node node, List<Integer> result) {
        while (node != null) {
            if (!isLeaf(node)) result.add(node.data);
            node = (node.left != null) ? node.left : node.right;
        }
    }

    private void addRightBoundary(Node node, List<Integer> result) {
        while (node != null) {
            if (!isLeaf(node)) result.add(node.data);
            node = (node.right != null) ? node.right : node.left;
        }
    }

    private void addLeaves(Node node, List<Integer> result) {
        if (node == null) return;
        if (isLeaf(node)) {
            result.add(node.data);
            return;
        }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    private void addLeavesIteratively(Node node, List<Integer> result) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (isLeaf(curr)) result.add(curr.data);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
    }
}
