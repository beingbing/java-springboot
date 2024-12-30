package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class S002_gfg_top_view {
    public ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<NodeHorizontalDistancePair> queue = new LinkedList<>();
        int maxHorizontalDistance = Integer.MIN_VALUE;
        int minHorizontalDistance = Integer.MAX_VALUE;

        queue.add(new NodeHorizontalDistancePair(root, 0));

        while (!queue.isEmpty()) {
            NodeHorizontalDistancePair current = queue.poll();
            Node node = current.node;
            int hd = current.hd;

            maxHorizontalDistance = Math.max(maxHorizontalDistance, hd);
            minHorizontalDistance = Math.min(minHorizontalDistance, hd);

            map.putIfAbsent(hd, node.data); // Add the node to the map if it is the first node at this HD

            if (node.left != null) queue.add(new NodeHorizontalDistancePair(node.left, hd - 1));
            if (node.right != null) queue.add(new NodeHorizontalDistancePair(node.right, hd + 1));
        }

        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) result.add(map.get(i));
        return result;
    }
}
