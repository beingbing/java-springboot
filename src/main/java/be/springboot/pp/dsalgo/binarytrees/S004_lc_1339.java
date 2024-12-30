package be.springboot.pp.dsalgo.binarytrees;

public class S004_lc_1339 {
    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    private long calculateTotalSum(Node root) {
        if (root == null) return 0;
        return root.data + calculateTotalSum(root.left) + calculateTotalSum(root.right);
    }

    private long calculateSubtreeSum(Node root) {
        if (root == null) return 0;
        long subtreeSum = root.data + calculateSubtreeSum(root.left) + calculateSubtreeSum(root.right);

        long complementSum = totalSum - subtreeSum;
        maxProduct = Math.max(maxProduct, subtreeSum * complementSum);

        return subtreeSum;
    }

    public int maxProduct(Node root) {
        totalSum = calculateTotalSum(root); // Step 1: Calculate the total sum of the tree
        calculateSubtreeSum(root); // Step 2: Find the maximum product by calculating subtree sums
        return (int) (maxProduct % MOD); // Step 3: Return the result modulo MOD
    }
}
