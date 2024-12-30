package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.Stack;

public class S003_gfg_construct_tree {
    private int index = 0;

    public Node constructTree(int[] pre, char[] preLN) {
        if (pre == null || pre.length == 0 || pre.length != preLN.length) return null;
        return buildTree(pre, preLN);
    }

    private Node buildTree(int[] pre, char[] preLN) {
        if (index >= pre.length) return null;
        Node node = new Node(pre[index]);

        // Check if it's a leaf node
        if (preLN[index] == 'L') {
            index++; // Move to the next node
            return node;
        }

        // If it's a non-leaf node, build left and right subtrees
        index++; // Move to the left child
        node.left = buildTree(pre, preLN);
        node.right = buildTree(pre, preLN); // Move to the right child

        return node;
    }
}

class S003_gfg_construct_tree_iterative {
    public Node constructTree(int n, ArrayList<Integer> pre, ArrayList<Character> preLN) {
        if (n == 0) return null;
        Stack<Node> st = new Stack<>();
        Node root = new Node(pre.getFirst());

        if (preLN.getFirst() != 'L') st.push(root);

        for (int i = 1; i < n; i++) {
            Node curr = new Node(pre.get(i));

            if (st.peek().left == null) st.peek().left = curr;

            else if (st.peek().right == null) {
                st.peek().right = curr;
                st.pop();
            }

            if (preLN.get(i) != 'L') st.push(curr);
        }

        return root;
    }
}
