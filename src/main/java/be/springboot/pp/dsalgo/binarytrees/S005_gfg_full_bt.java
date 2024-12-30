package be.springboot.pp.dsalgo.binarytrees;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class S005_gfg_full_bt {
    private int preIndex = 0;
    private final Map<Integer, Integer> mirrorIndexMap = new HashMap<>();

    public Node constructBTree(int[] preOrder, int[] preOrderMirror, int size) {
        for (int i = 0; i < size; i++) mirrorIndexMap.put(preOrderMirror[i], i);
        return buildTree(preOrder, 0, size - 1, size);
    }

    private Node buildTree(int[] preOrder, int left, int right, int size) {
        if (left > right || preIndex >= size) return null;
        Node root = new Node(preOrder[preIndex++]);
        if (left == right) return root;
        int mirrorIndex = mirrorIndexMap.get(preOrder[preIndex]); // Find index of the next element in mirror preorder

        if (mirrorIndex <= right) {
            root.left = buildTree(preOrder, mirrorIndex, right, size);
            root.right = buildTree(preOrder, left + 1, mirrorIndex - 1, size);
        }
        return root;
    }
}

class S005_gfg_full_bt_iterative {
    public Node constructBTree(int[] preorder, int[] preorderMirror, int size) {
        if (size == 0) return null;

        Map<Integer, Integer> mirrorIndexMap = new HashMap<>(); // Step 1: Create a map to store indices of elements in the mirror preorder array
        for (int i = 0; i < size; i++) mirrorIndexMap.put(preorderMirror[i], i);

        Node root = new Node(preorder[0]); // Step 2: Initialize the root of the tree and a stack to assist in iterative tree construction
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        for (int i = 1; i < size; i++) { // Step 3: Iterate over the preorder array to construct the tree
            int currentData = preorder[i];
            Node currentNode = new Node(currentData);

            while (!stack.isEmpty()) { // Step 4: Adjust the stack to find the correct parent node for the current node
                Node parent = stack.peek();

                if (mirrorIndexMap.get(parent.data) < mirrorIndexMap.get(currentData)) { // Compare positions in the mirror preorder array to determine placement
                    if (parent.left == null) { // Place the node as the left child
                        parent.left = currentNode;
                        stack.push(currentNode);
                    } else { // Place the node as the right child
                        parent.right = currentNode;
                        stack.pop(); // Parent is now complete
                        stack.push(currentNode);
                    }
                    break;
                } else stack.pop(); // Remove completed nodes from the stack
            }
        }

        return root; // Return the constructed tree
    }
}

class S005_gfg_full_bt_m2 {
    private int preIndex = 0;
    private int postIndex = 0;

    public Node constructBTree(int[] preOrder, int[] preOrderMirror, int size) {
        int[] postOrder = newReverseArray(preOrderMirror, size);
        return constructFromPrePost(preOrder, postOrder);
    }

    public Node constructFromPrePost(int[] preorder, int[] postorder) {
        Node root = new Node(preorder[preIndex++]);
        if (root.data != postorder[postIndex]) root.left = constructFromPrePost(preorder, postorder);
        if (root.data != postorder[postIndex]) root.right = constructFromPrePost(preorder, postorder);
        postIndex++;
        return root;
    }

    private int[] newReverseArray(int[] mirrorPreOrder, int size) {
        int[] postOrder = new int[size];
        for (int i = 0; i < size; i++) postOrder[size - i - 1] = mirrorPreOrder[i];
        return postOrder;
    }
}
