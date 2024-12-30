package be.springboot.pp.dsalgo.binarytrees;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class S002_lc_0106 {
    private Map<Integer, Integer> inorderMap;
    private int postorderIndex;

    public Node buildTree(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();
        postorderIndex = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);
        return buildSubtree(postorder, 0, inorder.length - 1);
    }

    private Node buildSubtree(int[] postorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) return null;
        int rootValue = postorder[postorderIndex--];
        Node root = new Node(rootValue);

        // Build right and left subtrees (right subtree first since postorder traversal processes it last)
        int rootIndexInInorder = inorderMap.get(rootValue);
        root.right = buildSubtree(postorder, rootIndexInInorder + 1, inorderEnd);
        root.left = buildSubtree(postorder, inorderStart, rootIndexInInorder - 1);

        return root;
    }
}

class S002_lc_0106_iterative {
    public Node buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length == 0) return null;

        Stack<Node> stack = new Stack<>();
        Node root = new Node(postorder[postorder.length - 1]);
        stack.push(root);

        int inorderIndex = inorder.length - 1;

        for (int i = postorder.length - 2; i >= 0; i--) {
            Node current = new Node(postorder[i]);
            Node top = stack.peek();

            if (top.data != inorder[inorderIndex]) {
                // Add as the right child
                top.right = current;
                stack.push(current);
            } else {
                // Backtrack to find the parent for the left child
                while (!stack.isEmpty() && stack.peek().data == inorder[inorderIndex]) {
                    top = stack.pop();
                    inorderIndex--;
                }
                // Add as the left child
                top.left = current;
                stack.push(current);
            }
        }

        return root;
    }
}
