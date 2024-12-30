package be.springboot.pp.dsalgo.binarytrees;

public class S002_lc_0404 {
    public int sumOfLeftLeaves(Node root) {
        if (root == null) return 0;
        int sum = 0;
        // Check if the left child is a leaf
        if (root.left != null && root.left.left == null && root.left.right == null) sum += root.left.data;
        return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right); // Recur for left and right subtrees
    }

    public int sumOfLeftLeavesM2(Node root) {
        if (root == null) return 0;
        return countOfLeftLeaves(root, false);
    }

    private int countOfLeftLeaves(Node node, boolean isLeft) {
        if (node.left == null && node.right == null) return isLeft ? node.data : 0;
        int sum = 0;
        if (node.left != null) sum += countOfLeftLeaves(node.left, true);
        if (node.right != null) sum += countOfLeftLeaves(node.right, false);
        return sum;
    }
}
