package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class S005_lc_0145 {
    public List<Integer> postorderTraversalRecursive(Node root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        postorderHelper(node.left, result);
        postorderHelper(node.right, result);
        result.add(node.data);
    }

    public List<Integer> postorderTraversalIterative(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            result.add(current.data);
            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }

        Collections.reverse(result);
        return result;
    }
}
