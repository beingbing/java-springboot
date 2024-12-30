package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class S001_gfg_pth_common_anc_optimal {
    private int result = -1;

    public int findKthAncestor(TreeNode root, int k, int target) {
        List<Integer> pathStack = new ArrayList<>(); // Stack-like list to store the path from root to target
        findNode(root, target, pathStack, k);
        return result;
    }

    private boolean findNode(TreeNode current, int target, List<Integer> pathStack, int generationsToAncestor) {
        if (current == null) return false;
        pathStack.add(current.data);

        if (current.data == target) {
            calculateKthAncestor(pathStack, generationsToAncestor);
            return true;
        }

        if (findNode(current.left, target, pathStack, generationsToAncestor)
                || findNode(current.right, target, pathStack, generationsToAncestor))
            return true;

        pathStack.removeLast();
        return false;
    }

    private void calculateKthAncestor(List<Integer> pathStack, int generationsToAncestor) {
        int index = pathStack.size() - 1 - generationsToAncestor; // Index of the kth ancestor
        if (index >= 0) result = pathStack.get(index); // Fetch the kth ancestor from the stack
        else result = -1; // If no such ancestor exists
    }
}

public class S001_gfg_pth_common_anc {
    public int kthAncestor(TreeNode root, int k, int node) {
        Map<Integer, Integer> parentMap = new HashMap<>(); // Map to store parent of each node
        populateParentMap(root, null, parentMap); // Populate the parent map using a DFS
        int current = node; // Start from the given node
        while (k > 0 && parentMap.containsKey(current)) { // Traverse upwards k times
            current = parentMap.get(current);
            k--;
        }

        return k == 0 ? current : -1; // If k is still greater than 0, no such ancestor exists
    }

    private void populateParentMap(TreeNode node, TreeNode parent, Map<Integer, Integer> parentMap) {
        if (node == null) return;
        if (parent != null) parentMap.put(node.data, parent.data); // If the node has a parent, add it to the map

        populateParentMap(node.left, node, parentMap);
        populateParentMap(node.right, node, parentMap);
    }
}
