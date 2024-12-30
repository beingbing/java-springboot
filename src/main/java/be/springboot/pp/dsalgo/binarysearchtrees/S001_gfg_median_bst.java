package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.ArrayList;
import java.util.List;

public class S001_gfg_median_bst {
    public float findMedian(TreeNode root) {
        if (root == null) return 0; // Edge case: Empty tree
        List<Integer> sortedValues = new ArrayList<>(); // Step 1: Perform an in-order traversal to collect node values in sorted order
        inorderTraversal(root, sortedValues);
        int size = sortedValues.size(); // Step 2: Calculate the median
        if (size % 2 == 0) return (float)((sortedValues.get(size / 2) + sortedValues.get((size / 2) - 1)) / 2.0); // If the size is even, the median is the average of the two middle elements
        else return sortedValues.get(size / 2); // If the size is odd, the median is the middle element
    }

    private void inorderTraversal(TreeNode root, List<Integer> values) {
        if (root == null) return;
        inorderTraversal(root.left, values);
        values.add(root.data);
        inorderTraversal(root.right, values);
    }
}
