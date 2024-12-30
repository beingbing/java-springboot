package be.springboot.pp.dsalgo.binarytrees;

public class S001_lc_1026 {
    public int maxAncestorDiff(Node root) {
        return dfs(root, root.data, root.data);
    }

    private int dfs(Node node, int minVal, int maxVal) {
        if (node == null) return maxVal - minVal;

        minVal = Math.min(minVal, node.data);
        maxVal = Math.max(maxVal, node.data);

        int leftDiff = dfs(node.left, minVal, maxVal);
        int rightDiff = dfs(node.right, minVal, maxVal);

        return Math.max(leftDiff, rightDiff);
    }
}
