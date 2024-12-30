package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.HashSet;

public class S001_gfg_brother_roots {

    public int countPairs(TreeNode root1, TreeNode root2, int x) {
        HashSet<Integer> values = new HashSet<>(); // HashSet to store values from the first BST
        storeInSet(root1, values); // Populate the set with in-order traversal of the first BST
        return countPairsInSecondBST(root2, values, x); // Count pairs during in-order traversal of the second BST
    }

    private void storeInSet(TreeNode node, HashSet<Integer> set) {
        if (node == null) return;
        storeInSet(node.left, set);   // Traverse left
        set.add(node.data);            // Add current node value
        storeInSet(node.right, set);  // Traverse right
    }

    private int countPairsInSecondBST(TreeNode node, HashSet<Integer> set, int target) {
        if (node == null) return 0;
        int count = 0; // Initialize count
        if (set.contains(target - node.data)) count++; // Check if complement exists in the set

        count += countPairsInSecondBST(node.left, set, target);
        count += countPairsInSecondBST(node.right, set, target);

        return count;
    }

}
