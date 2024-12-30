package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

class NodeDiagonalPair {
    Node node;
    int diagonal;

    NodeDiagonalPair(Node node, int hd) {
        this.node = node;
        this.diagonal = hd;
    }
}

public class S003_gfg_diagonal_traverse {
    private int maxHorizontalDistance = Integer.MIN_VALUE;
    private int minHorizontalDistance = Integer.MAX_VALUE;

    public ArrayList<Integer> diagonal(Node root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        traverse(root, 0, map);

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) result.addAll(map.get(i));
        return result;
    }

    private void traverse(Node node, int diagonal, HashMap<Integer, List<Integer>> map) {
        if (node == null) return;

        maxHorizontalDistance = Math.max(maxHorizontalDistance, diagonal);
        minHorizontalDistance = Math.min(minHorizontalDistance, diagonal);

        map.computeIfAbsent(diagonal, k -> new ArrayList<>()).add(node.data);

        traverse(node.left, diagonal + 1, map); // Move to left child will give a new diagonal
        traverse(node.right, diagonal, map);    // Stay on the same diagonal when moved to right child
    }
}

class S003_gfg_diagonal_traverse_iterative {
    public ArrayList<Integer> diagonal(Node root) {
        if (root == null) return new ArrayList<>();

        ArrayList<Integer> result = new ArrayList<>();
        Queue<NodeDiagonalPair> queue = new LinkedList<>();

        // Start with the root at diagonal level 0
        queue.offer(new NodeDiagonalPair(root, 0));
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxDiagonal = Integer.MIN_VALUE;
        int minDiagonal = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            NodeDiagonalPair current = queue.poll();
            Node node = current.node;
            int diagonal = current.diagonal;

            maxDiagonal = Math.max(maxDiagonal, diagonal);
            minDiagonal = Math.min(minDiagonal, diagonal);

            map.computeIfAbsent(diagonal, k -> new ArrayList<>()).add(node.data);

            if (node.left != null) queue.offer(new NodeDiagonalPair(node.left, diagonal + 1)); // Move to next diagonal
            if (node.right != null) queue.offer(new NodeDiagonalPair(node.right, diagonal));   // Stay on same diagonal
        }

        for (int i = minDiagonal; i <= maxDiagonal; i++) result.addAll(map.get(i));
        return result;
    }
}