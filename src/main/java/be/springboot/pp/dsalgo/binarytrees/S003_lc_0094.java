package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class S003_lc_0094 {
    public List<Integer> inorderTraversalRecursive(Node root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.data);
        inorderHelper(node.right, result);
    }

    public List<Integer> inorderTraversalIterative(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.data);
            current = current.right;
        }
        return result;
    }
}
