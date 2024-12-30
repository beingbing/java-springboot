package be.springboot.pp.dsalgo.binarysearchtrees;

public class S004_lc_1373 {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        return Math.max(findLargestBSTSum(root).sum, maxSum);
    }

    private SubtreeFeatures findLargestBSTSum(TreeNode node) {
        if (node == null) return new SubtreeFeatures(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        SubtreeFeatures left = findLargestBSTSum(node.left);
        SubtreeFeatures right = findLargestBSTSum(node.right);

        if (left.isBST && right.isBST && node.data > left.max && node.data < right.min) {
            int sum = node.data + left.sum + right.sum;
            maxSum = Math.max(maxSum, sum);
            int minVal = Math.min(node.data, left.min);
            int maxVal = Math.max(node.data, right.max);
            return new SubtreeFeatures(true, sum, minVal, maxVal);
        }

        return new SubtreeFeatures(false, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
}

class SubtreeFeatures {
    boolean isBST;
    int sum;
    int min;
    int max;

    SubtreeFeatures(boolean isBST, int sum, int min, int max) {
        this.isBST = isBST;
        this.sum = sum;
        this.min = min;
        this.max = max;
    }
}
