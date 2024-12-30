package be.springboot.pp.dsalgo.binarysearchtrees;

public class S004_lc_0108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return constructBST(nums, 0, nums.length - 1);
    }

    private TreeNode constructBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2; // Find the middle element
        TreeNode node = new TreeNode(nums[mid]); // Create a node with the middle element
        node.left = constructBST(nums, start, mid - 1); // Recursively construct the left and right subtrees
        node.right = constructBST(nums, mid + 1, end);
        return node;
    }
}
