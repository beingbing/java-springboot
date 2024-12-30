package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S002_lc_0103 {
    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result; // Return empty list for empty tree

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true; // Start with left-to-right traversal

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                currentLevel.add(currentNode.data);

                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }

            if (!leftToRight) Collections.reverse(currentLevel);

            result.add(currentLevel);
            leftToRight = !leftToRight; // Toggle the direction
        }

        return result;
    }
}
