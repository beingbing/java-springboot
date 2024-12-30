package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class S003_gfg_bottom_view {
    public ArrayList<Integer> bottomView(Node root) {
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

            map.put(hd, node.data);

            if (node.left != null) queue.add(new NodeHorizontalDistancePair(node.left, hd - 1));
            if (node.right != null) queue.add(new NodeHorizontalDistancePair(node.right, hd + 1));
        }

        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) result.add(map.get(i));
        return result;
    }
}
