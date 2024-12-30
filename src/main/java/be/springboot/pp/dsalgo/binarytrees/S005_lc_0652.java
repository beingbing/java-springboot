package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S005_lc_0652 {
    public List<Node> findDuplicateSubtrees(Node root) {
        Map<String, Integer> subtreeMap = new HashMap<>(); // Map to store serialized subtree and its frequency
        List<Node> result = new ArrayList<>(); // List to store roots of duplicate subtrees
        serializeSubtree(root, subtreeMap, result);
        return result;
    }

    private String serializeSubtree(Node node, Map<String, Integer> subtreeMap, List<Node> result) {
        if (node == null) return "#"; // Use "#" as a marker for null nodes
        String serial = node.data + "," + serializeSubtree(node.left, subtreeMap, result) + "," + serializeSubtree(node.right, subtreeMap, result); // Serialize the subtree in postorder: left, right, root
        subtreeMap.put(serial, subtreeMap.getOrDefault(serial, 0) + 1); // Update the frequency in the map
        if (subtreeMap.get(serial) == 2) result.add(node); // If this is the second time we've seen this subtree, add its root to the result
        return serial;
    }
}
