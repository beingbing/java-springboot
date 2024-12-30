package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class S001_lc_0144 {
    private void preorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        result.add(node.data);
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    public List<Integer> preorderTraversalRecursive(Node root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    public List<Integer> preorderTraversalIterative(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            result.add(current.data);
            if (current.right != null) stack.push(current.right); // Push right child first so left child is processed first
            if (current.left != null) stack.push(current.left);
        }

        return result;
    }
}
