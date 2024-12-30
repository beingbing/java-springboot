package be.springboot.pp.dsalgo.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class S001_lc_0623 {
    public Node addOneRow(Node root, int val, int depth) {
        if (depth == 1) { // If depth is 1, create a new root with the current tree as its left subtree
            Node newRoot = new Node(val);
            newRoot.left = root;
            return newRoot;
        }

        addRow(root, val, depth, 1);
        return root;
    }

    private void addRow(Node node, int val, int depth, int currentDepth) {
        if (node == null) return;

        if (currentDepth == depth - 1) {
            Node leftSubtree = node.left;
            Node rightSubtree = node.right;

            node.left = new Node(val);
            node.left.left = leftSubtree;

            node.right = new Node(val);
            node.right.right = rightSubtree;
        } else {
            addRow(node.left, val, depth, currentDepth + 1);
            addRow(node.right, val, depth, currentDepth + 1);
        }
    }
}

class S001_lc_0623_iterative {
    public Node addOneRow(Node root, int val, int depth) {
        if (depth == 1) { // If depth is 1, create a new root with the current tree as its left subtree
            Node newRoot = new Node(val);
            newRoot.left = root;
            return newRoot;
        }

        Queue<Node> queue = new LinkedList<>(); // Use a queue for level order traversal
        queue.add(root);
        int currentDepth = 1;

        while (!queue.isEmpty() && currentDepth < depth - 1) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            currentDepth++;
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Node leftSubtree = node.left;
            Node rightSubtree = node.right;

            node.left = new Node(val);
            node.left.left = leftSubtree;

            node.right = new Node(val);
            node.right.right = rightSubtree;
        }

        return root;
    }
}
