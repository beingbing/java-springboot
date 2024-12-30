package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S002_gfg_vertical_sum {
    private int maxHorizontalDistance = Integer.MIN_VALUE;
    private int minHorizontalDistance = Integer.MAX_VALUE;

    public List<Integer> verticalSum(Node root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        computeVerticalSum(root, 0, map);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) result.add(map.get(i));
        return result;
    }

    private void computeVerticalSum(Node node, int horizontalDistance, HashMap<Integer, Integer> map) {
        if (node == null) return;

        maxHorizontalDistance = Math.max(maxHorizontalDistance, horizontalDistance);
        minHorizontalDistance = Math.min(minHorizontalDistance, horizontalDistance);

        // Add current node's value to its horizontal distance's sum
        map.put(horizontalDistance, map.getOrDefault(horizontalDistance, 0) + node.data);

        // Recur for left and right subtrees
        computeVerticalSum(node.left, horizontalDistance - 1, map);
        computeVerticalSum(node.right, horizontalDistance + 1, map);
    }
}

class S002_gfg_vertical_sum_iterative {
    public ArrayList<Integer> verticalSum(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<NodeHorizontalDistancePair> queue = new LinkedList<>();
        int maxHorizontalDistance = Integer.MIN_VALUE;
        int minHorizontalDistance = Integer.MAX_VALUE;

        queue.offer(new NodeHorizontalDistancePair(root, 0));

        while (!queue.isEmpty()) {
            NodeHorizontalDistancePair current = queue.poll();
            Node node = current.node;
            int hd = current.hd;

            maxHorizontalDistance = Math.max(maxHorizontalDistance, hd);
            minHorizontalDistance = Math.min(minHorizontalDistance, hd);

            // Add node value to its vertical line sum
            map.put(hd, map.getOrDefault(hd, 0) + node.data);

            // Add left and right children to the queue
            if (node.left != null) queue.offer(new NodeHorizontalDistancePair(node.left, hd - 1));
            if (node.right != null) queue.offer(new NodeHorizontalDistancePair(node.right, hd + 1));
        }

        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) result.add(map.get(i));
        return result;
    }
}
