package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S001_gfg_max_width {
    public int getMaxWidth(Node root) {
        List<Integer> levelCounts = new ArrayList<>();
        countNodesAtEachLevel(root, 0, levelCounts);

        int maxWidth = 0;
        for (int count : levelCounts) maxWidth = Math.max(maxWidth, count);
        return maxWidth;
    }

    private void countNodesAtEachLevel(Node node, int level, List<Integer> levelCounts) {
        if (node == null) return;

        if (levelCounts.size() <= level) levelCounts.add(0);
        levelCounts.set(level, levelCounts.get(level) + 1); // Increment the count for the current level

        countNodesAtEachLevel(node.left, level + 1, levelCounts);
        countNodesAtEachLevel(node.right, level + 1, levelCounts);
    }
}

class S001_gfg_max_width_iterative {
    public int getMaxWidth(Node root) {
        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelWidth = queue.size(); // Nodes at the current level
            maxWidth = Math.max(maxWidth, levelWidth);

            for (int i = 0; i < levelWidth; i++) {
                Node current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }

        return maxWidth;
    }
}
