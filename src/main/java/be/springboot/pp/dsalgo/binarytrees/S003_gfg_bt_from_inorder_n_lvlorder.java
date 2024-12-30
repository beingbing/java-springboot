package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class S003_gfg_bt_from_inorder_n_lvlorder {
    public Node buildTree(int[] inorder, int[] levelOrder) {
        if (inorder == null || levelOrder == null || inorder.length != levelOrder.length) return null;

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);

        List<Integer> levelList = new ArrayList<>();
        for (int num : levelOrder) levelList.add(num);

        return buildSubTree(inorder, 0, inorder.length - 1, levelList, inorderMap);
    }

    private Node buildSubTree(int[] inorder, int inStart, int inEnd, List<Integer> levelList, Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd || levelList.isEmpty()) return null;

        // First element in levelList is the root for the current subtree
        int rootVal = levelList.removeFirst();
        Node root = new Node(rootVal);

        int rootIndex = inorderMap.get(rootVal);

        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();
        for (int num : levelList)
            if (inorderMap.get(num) < rootIndex) leftSubtree.add(num);
            else rightSubtree.add(num);

        root.left = buildSubTree(inorder, inStart, rootIndex - 1, leftSubtree, inorderMap);
        root.right = buildSubTree(inorder, rootIndex + 1, inEnd, rightSubtree, inorderMap);

        return root;
    }
}

class S003_gfg_bt_from_inorder_n_lvlorder_iterative {
    public Node buildTree(int[] inorder, int[] levelOrder) {
        if (inorder == null || levelOrder == null || inorder.length != levelOrder.length) return null; // Edge case: Invalid input

        int n = inorder.length;

        // Step 1: Create a map to store the index of each node in the inorder array
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) inorderIndexMap.put(inorder[i], i);

        Node root = new Node(levelOrder[0]); // Step 2: Create the root node from the first element in levelOrder

        Queue<Node> queue = new LinkedList<>(); // Step 3: Use a queue to build the tree iteratively
        queue.add(root);

        for (int i = 1; i < n; i++) { // Step 4: Process the rest of the levelOrder array to build the tree
            Node currentNode = new Node(levelOrder[i]);
            Node parentNode = queue.peek(); // Get the parent node from the front of the queue

            // Step 5: Compare positions in the inorder array to determine left or right child
            if (inorderIndexMap.get(currentNode.data) < inorderIndexMap.get(parentNode.data)) parentNode.left = currentNode; // Assign as left child
            else {
                parentNode.right = currentNode; // Assign as right child
                queue.poll(); // Remove the parent node as both children are assigned
            }

            queue.add(currentNode); // Add the current node to the queue for further processing
        }

        return root; // Return the constructed tree
    }
}
