package be.springboot.pp.dsalgo.binarytrees;

import java.util.HashMap;

public class S006_lc_0437 {
    public int pathSum(Node root, int targetSum) {
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Base case: One way to get a sum of 0 (no nodes included)
        return dfs(root, 0, targetSum, prefixSumCount);
    }

    private int dfs(Node node, int currentSum, int targetSum, HashMap<Integer, Integer> prefixSumCount) {
        if (node == null) return 0;
        currentSum += node.data;
        int paths = prefixSumCount.getOrDefault(currentSum - targetSum, 0); // Check how many times (currentSum - targetSum) has occurred
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1); // Update the prefixSumCount map for the currentSum

        paths += dfs(node.left, currentSum, targetSum, prefixSumCount);
        paths += dfs(node.right, currentSum, targetSum, prefixSumCount);

        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1); // Backtrack: Remove the current sum from the prefixSumCount
        return paths;
    }
}
