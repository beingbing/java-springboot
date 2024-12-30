package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class NodeHorizontalDistancePair {
    Node node;
    int hd;

    NodeHorizontalDistancePair(Node node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}

public class S001_gfg_vertical_order {
    public List<Integer> verticalTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<NodeHorizontalDistancePair> queue = new LinkedList<>();
        int maxHorizontalDistance = Integer.MIN_VALUE;
        int minHorizontalDistance = Integer.MAX_VALUE;

        queue.add(new NodeHorizontalDistancePair(root, 0)); // Start BFS with the root and HD = 0

        while (!queue.isEmpty()) {
            NodeHorizontalDistancePair current = queue.poll();
            Node node = current.node;
            int hd = current.hd;

            maxHorizontalDistance = Math.max(maxHorizontalDistance, hd);
            minHorizontalDistance = Math.min(minHorizontalDistance, hd);

            // Add the node's value to the corresponding vertical level
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.data);

            // Add children to the queue with updated horizontal distances
            if (node.left != null) queue.add(new NodeHorizontalDistancePair(node.left, hd - 1));
            if (node.right != null) queue.add(new NodeHorizontalDistancePair(node.right, hd + 1));
        }

        // Collect all values from the TreeMap
        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) result.addAll(map.get(i));

        return result;
    }
}

class S001_gfg_vertical_order_recursion {
    private int maxHorizontalDistance = Integer.MIN_VALUE;
    private int minHorizontalDistance = Integer.MAX_VALUE;

    public ArrayList<Integer> verticalOrder(Node root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        dfs(root, 0, map);

        // Collect all values from the TreeMap
        ArrayList<Integer> result = new ArrayList<>();
        if (map.isEmpty()) return result;
        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) result.addAll(map.get(i));
        return result;
    }

    private void dfs(Node node, int hd, HashMap<Integer, List<Integer>> map) {
        if (node == null) return;

        maxHorizontalDistance = Math.max(maxHorizontalDistance, hd);
        minHorizontalDistance = Math.min(minHorizontalDistance, hd);

        // Add the node's value to the corresponding vertical level
        map.putIfAbsent(hd, new ArrayList<>());
        map.get(hd).add(node.data);

        // Recur for left and right subtrees with updated horizontal distances
        dfs(node.left, hd - 1, map);
        dfs(node.right, hd + 1, map);
    }
}