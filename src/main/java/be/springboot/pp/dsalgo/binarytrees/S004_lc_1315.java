package be.springboot.pp.dsalgo.binarytrees;

public class S004_lc_1315 {
    public int sumEvenGrandparent(Node root) {
        return dfs(root, null, null);
    }

    private int dfs(Node node, Node parent, Node grandparent) {
        if (node == null) return 0; // Base case: no node to process

        int sum = 0;
        if (grandparent != null && grandparent.data % 2 == 0) sum += node.data; // Add node's value if grandparent is even

        // Recur for left and right children, updating parent and grandparent
        sum += dfs(node.left, node, parent);
        sum += dfs(node.right, node, parent);

        return sum;
    }
}
