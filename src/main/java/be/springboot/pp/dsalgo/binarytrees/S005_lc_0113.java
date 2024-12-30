package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class S005_lc_0113 {
    public List<List<Integer>> pathSum(Node root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, targetSum, currentPath, result);
        return result;
    }

    private void dfs(Node node, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;
        currentPath.add(node.data);

        if (node.left == null && node.right == null && targetSum == node.data) result.add(new ArrayList<>(currentPath)); // Check if the current node is a leaf and its value equals the remaining targetSum
        else {
            dfs(node.left, targetSum - node.data, currentPath, result);
            dfs(node.right, targetSum - node.data, currentPath, result);
        }

        currentPath.removeLast(); // Backtrack: remove the current node from the path
    }
}
