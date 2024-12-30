package be.springboot.pp.dsalgo.binarytrees;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class S001_lc_0105 {
    private Map<Integer, Integer> inorderMap;
    private int preorderIndex;

    public Node buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        preorderIndex = 0;

        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i); // Map inorder values to their indices for quick access
        return buildSubtree(preorder, 0, inorder.length - 1);
    }

    private Node buildSubtree(int[] preorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) return null;
        int rootValue = preorder[preorderIndex++]; // Get the current root value from preorder and increment the index
        Node root = new Node(rootValue);

        int rootIndexInInorder = inorderMap.get(rootValue);
        root.left = buildSubtree(preorder, inorderStart, rootIndexInInorder - 1);
        root.right = buildSubtree(preorder, rootIndexInInorder + 1, inorderEnd);

        return root;
    }
}

class S001_lc_0105_iterative {
    public Node buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0) return null;

        Stack<Node> stack = new Stack<>();
        Node root = new Node(preorder[0]);
        stack.push(root);

        int inorderIndex = 0;

        for (int i = 1; i < preorder.length; i++) {
            int currentValue = preorder[i];
            Node node = stack.peek();

            if (node.data != inorder[inorderIndex]) { // Left child case
                node.left = new Node(currentValue);
                stack.push(node.left);
            } else { // Process the stack to find the parent for the right child
                while (!stack.isEmpty() && stack.peek().data == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                // Right child case
                node.right = new Node(currentValue);
                stack.push(node.right);
            }
        }

        return root;
    }
}
