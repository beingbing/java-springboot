package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S004_gfg_right_view {
    public List<Integer> rightView(Node root) {
        List<Integer> result = new ArrayList<>();
        int[] maxLevel = {0}; // To track the maximum level visited
        rightViewRecursive(root, result, 1, maxLevel);
        return result;
    }

    private void rightViewRecursive(Node root, List<Integer> result, int level, int[] maxLevel) {
        if (root == null) return;

        if (level > maxLevel[0]) {
            result.add(root.data);
            maxLevel[0] = level;
        }
        rightViewRecursive(root.right, result, level + 1, maxLevel);
        rightViewRecursive(root.left, result, level + 1, maxLevel);
    }
}

class S004_gfg_right_view_iteration {
    public List<Integer> rightView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node lastNode = null;

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                lastNode = currentNode;

                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }

            if (lastNode != null) result.add(lastNode.data);
        }

        return result;
    }
}
