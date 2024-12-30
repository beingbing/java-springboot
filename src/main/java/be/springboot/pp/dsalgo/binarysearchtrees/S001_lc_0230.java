package be.springboot.pp.dsalgo.binarysearchtrees;

public class S001_lc_0230 {
    private int counter = 0; // Counter to track the number of nodes visited
    private final int NOT_FOUND = -1;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return NOT_FOUND;
        int left = kthSmallest(root.left, k);
        if (left != NOT_FOUND) return left;
        if (++counter == k) return root.data;
        return kthSmallest(root.right, k);
    }

    // follow-up
    private int kthSmallestWithSize(TreeNodeWithSize root, int k) {
        if (root == null) return -1;
        int leftSize = (root.left != null) ? root.left.subtreeSize : 0;

        if (k == leftSize + 1) return root.val; // The current node is the k-th smallest
        else if (k <= leftSize) return kthSmallestWithSize(root.left, k); // Search in the left subtree
        else return kthSmallestWithSize(root.right, k - leftSize - 1); // Search in the right subtree with adjusted k
    }
}

class TreeNodeWithSize {
    int val;
    int subtreeSize; // Size of the subtree rooted at this node
    TreeNodeWithSize left, right;

    TreeNodeWithSize(int val) {
        this.val = val;
        this.subtreeSize = 1; // Initial size is 1 (itself)
    }
}
