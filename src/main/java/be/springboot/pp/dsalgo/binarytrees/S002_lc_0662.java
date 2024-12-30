package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S002_lc_0662 {
    public int widthOfBinaryTree(Node root) {
        List<Integer> firstPos = new ArrayList<>();
        return dfs(root, 0, 0, firstPos);
    }

    private int dfs(Node node, int level, int pos, List<Integer> firstPos) {
        if (node == null) return 0;

        // Record the first position encountered at this level
        if (level == firstPos.size()) firstPos.add(pos);

        int currentWidth = pos - firstPos.get(level) + 1;
        int leftWidth = dfs(node.left, level + 1, 2 * pos, firstPos);
        int rightWidth = dfs(node.right, level + 1, 2 * pos + 1, firstPos);

        return Math.max(currentWidth, Math.max(leftWidth, rightWidth));
    }
}

class NodeIndexPair<T, U> {
    T node;
    U index;

    NodeIndexPair(T node, U index) {
        this.node = node;
        this.index = index;
    }
}

class S002_lc_0662_iterative {
    public int widthOfBinaryTree(Node root) {
        if (root == null) return 0;

        Queue<NodeIndexPair<Node, Integer>> queue = new LinkedList<>();
        queue.add(new NodeIndexPair<>(root, 0));
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int first = queue.peek().index;
            int last = first; // Initialize last to the first node's index

            for (int i = 0; i < size; i++) {
                NodeIndexPair<Node, Integer> current = queue.poll();
                Node node = current.node;
                int index = current.index;

                last = index; // Update the last position
                if (node.left != null) queue.add(new NodeIndexPair<>(node.left, 2 * index));
                if (node.right != null) queue.add(new NodeIndexPair<>(node.right, 2 * index + 1));
            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }
}
