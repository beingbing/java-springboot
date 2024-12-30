package be.springboot.pp.dsalgo.binarytrees;

public class S002_lc_0979 {
    private int moves = 0;

    public int distributeCoins(Node root) {
        dfs(root);
        return moves;
    }

    private int dfs(Node node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int excess = node.data - 1 + left + right; // -1 coz we need to leave a coin for current node
        moves += Math.abs(excess);
        return excess;
    }
}
