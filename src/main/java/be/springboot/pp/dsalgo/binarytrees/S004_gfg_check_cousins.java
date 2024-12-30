package be.springboot.pp.dsalgo.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

class NodeInfo {
    Node node;
    Node parent;
    int depth;

    NodeInfo(Node node, Node parent, int depth) {
        this.node = node;
        this.parent = parent;
        this.depth = depth;
    }
}

public class S004_gfg_check_cousins {
    public boolean isCousins(Node root, int a, int b) {
        if (root == null) return false;

        // BFS traversal
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(root, null, 0));

        NodeInfo aInfo = null, bInfo = null;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                NodeInfo current = queue.poll();

                // Check if the current node is one of the targets
                if (current.node.data == a) aInfo = current;
                if (current.node.data == b) bInfo = current;

                // If both nodes are found at the same level
                if (aInfo != null && bInfo != null) break;

                // Add children to the queue
                if (current.node.left != null)
                    queue.add(new NodeInfo(current.node.left, current.node, current.depth + 1));
                if (current.node.right != null)
                    queue.add(new NodeInfo(current.node.right, current.node, current.depth + 1));
            }

            // If both nodes are found, check their parents
            if (aInfo != null && bInfo != null) {
                return aInfo.parent != bInfo.parent && aInfo.depth == bInfo.depth;
            }

            // If only one node is found at the current level, they can't be cousins
            if ((aInfo != null && bInfo == null) || (aInfo == null && bInfo != null)) {
                return false;
            }
        }

        return false;
    }
}
