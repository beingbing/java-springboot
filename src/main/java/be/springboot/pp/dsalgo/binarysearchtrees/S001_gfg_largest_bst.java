package be.springboot.pp.dsalgo.binarysearchtrees;

public class S001_gfg_largest_bst {

    public int largestBst(TreeNode root) {
        return findLargestBST(root).size;
    }

    private SubtreeDetails findLargestBST(TreeNode node) {
        if (node == null) return new SubtreeDetails(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE); // Base case: An empty subtree is a valid BST

        SubtreeDetails left = findLargestBST(node.left);
        SubtreeDetails right = findLargestBST(node.right);

        if (left.isBST && right.isBST && node.data > left.max && node.data < right.min) {
            int size = 1 + left.size + right.size;
            int min = Math.min(node.data, left.min);
            int max = Math.max(node.data, right.max);
            return new SubtreeDetails(true, size, min, max);
        }

        return new SubtreeDetails(false, Math.max(left.size, right.size), 0, 0);
    }
}

class SubtreeDetails {
    boolean isBST;
    int size;
    int min;
    int max;

    SubtreeDetails(boolean isBST, int size, int min, int max) {
        this.isBST = isBST;
        this.size = size;
        this.min = min;
        this.max = max;
    }
}
