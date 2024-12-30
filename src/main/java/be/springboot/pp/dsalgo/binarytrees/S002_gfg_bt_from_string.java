package be.springboot.pp.dsalgo.binarytrees;

import java.util.Stack;

public class S002_gfg_bt_from_string {
    private int index = 0; // Global index to track parsing position

    public Node str2tree(String s) {
        if (s.isEmpty()) return null;
        return buildTree(s);
    }

    private Node buildTree(String s) {
        if (index >= s.length()) return null;

        // Parse the integer value
        int start = index;
        while (index < s.length() && (Character.isDigit(s.charAt(index)) || s.charAt(index) == '-')) index++;
        int value = Integer.parseInt(s.substring(start, index));
        Node node = new Node(value);

        // Process left subtree
        if (index < s.length() && s.charAt(index) == '(') {
            index++; // Skip '('
            node.left = buildTree(s);
            index++; // Skip ')'
        }

        // Process right subtree
        if (index < s.length() && s.charAt(index) == '(') {
            index++; // Skip '('
            node.right = buildTree(s);
            index++; // Skip ')'
        }

        return node;
    }
}

class S002_gfg_bt_from_string_iterative {
    public Node str2tree(String s) {
        if (s.isEmpty()) return null;

        Stack<Node> stack = new Stack<>();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == ')') {
                stack.pop(); // End of a subtree
                i++;
            } else if (Character.isDigit(c) || c == '-') {
                // Parse integer
                int start = i;
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-')) i++;
                int value = Integer.parseInt(s.substring(start, i));
                Node node = new Node(value);

                // Attach to parent if available
                if (!stack.isEmpty()) {
                    Node parent = stack.peek();
                    if (parent.left == null) parent.left = node;
                    else parent.right = node;
                }
                stack.push(node); // Push current node
            } else if (c == '(') i++; // Start of a subtree
        }

        // Root is the last remaining node in the stack
        return stack.isEmpty() ? null : stack.pop();
    }
}
