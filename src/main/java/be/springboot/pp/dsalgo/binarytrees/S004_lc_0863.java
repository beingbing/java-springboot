package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class S004_lc_0863 {
    private final List<Integer> result = new ArrayList<>();
    private final Map<Node, Node> parentMap = new HashMap<>(); // Tracks the parent of each node
    private final Set<Node> visited = new HashSet<>(); // Tracks visited nodes to prevent revisiting

    public List<Integer> distanceK(Node root, Node target, int k) { // Public method to find all nodes at distance K
        if (root == null) return result; // Return an empty list for an empty tree
        buildParentMap(root, null); // Step 1: Build the parent map to record parent-child relationships
        findNodesAtDistanceK(target, k); // Step 2: Perform DFS to find nodes at distance K
        return result;
    }

    private void buildParentMap(Node currentNode, Node parentNode) {
        if (currentNode == null) return; // Base case: stop when reaching a null node
        if (parentNode != null) parentMap.put(currentNode, parentNode);
        buildParentMap(currentNode.left, currentNode);
        buildParentMap(currentNode.right, currentNode);
    }

    private void findNodesAtDistanceK(Node currentNode, int remainingDistance) {
        if (currentNode == null || visited.contains(currentNode)) return; // Stop if the node is null or already visited
        visited.add(currentNode);
        if (remainingDistance == 0) {
            result.add(currentNode.data);
            return;
        }
        findNodesAtDistanceK(currentNode.left, remainingDistance - 1);
        findNodesAtDistanceK(currentNode.right, remainingDistance - 1);
        findNodesAtDistanceK(parentMap.get(currentNode), remainingDistance - 1);
    }
}

class S004_lc_0863_graph {
    public List<Integer> distanceK(Node root, Node target, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        // Step 2: Perform BFS to find nodes at distance K
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        queue.add(target.data);
        visited.add(target.data);
        int distance = 0;

        while (!queue.isEmpty()) {
            if (distance == k) {
                // Collect all nodes at distance K
                result.addAll(queue);
                break;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            distance++;
        }

        return result;
    }

    private void buildGraph(Node node, Node parent, Map<Integer, List<Integer>> graph) {
        if (node == null) return;

        // Add edges between parent and current node
        if (parent != null) {
            graph.computeIfAbsent(node.data, k -> new ArrayList<>()).add(parent.data);
            graph.computeIfAbsent(parent.data, k -> new ArrayList<>()).add(node.data);
        }

        // Recursively process left and right children
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }
}
