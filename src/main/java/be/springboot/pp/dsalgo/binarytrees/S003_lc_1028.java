package be.springboot.pp.dsalgo.binarytrees;

import java.util.Stack;

public class S003_lc_1028 {
    public Node recoverFromPreorder(String traversal) {
        Stack<Node> stack = new Stack<>();
        int i = 0;
        while (i < traversal.length()) {
            int depth = 0;

            while (i < traversal.length() && traversal.charAt(i) == '-') { // Count dashes to determine depth
                depth++;
                i++;
            }

            // Read the node value
            int value = 0;
            while (i < traversal.length() && Character.isDigit(traversal.charAt(i))) {
                value = value * 10 + (traversal.charAt(i) - '0');
                i++;
            }

            Node node = new Node(value);

            // Maintain stack size corresponding to depth
            while (stack.size() > depth) stack.pop();

            if (!stack.isEmpty()) { // Attach the node as a left or right child
                Node parent = stack.peek();
                if (parent.left == null) parent.left = node;
                else parent.right = node;
            }

            stack.push(node);
        }

        // The root is the bottom-most element in the stack
        while (stack.size() > 1) stack.pop();
        return stack.peek();
    }
}
