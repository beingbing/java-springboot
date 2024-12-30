package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S003_gfg_left_view {
    public List<Integer> leftView(Node root) {
        List<Integer> result = new ArrayList<>();
        int maxLevel = 0; // To track the maximum level visited
        leftViewRecursive(root, result, 1, maxLevel);
        return result;
    }

    private void leftViewRecursive(Node root, List<Integer> result, int level, int maxLevel) {
        if (root == null) return;

        if (level > maxLevel) {
            result.add(root.data);
            maxLevel = level;
        }

        leftViewRecursive(root.left, result, level + 1, maxLevel);
        leftViewRecursive(root.right, result, level + 1, maxLevel);
    }
}

class S003_gfg_left_view_iteration {
    public List<Integer> leftView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                if (i == 0) result.add(currentNode.data);
                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
        }

        return result;
    }
}
