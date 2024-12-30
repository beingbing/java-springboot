package be.springboot.pp.dsalgo.binarytrees;

public class S001_gfg_count_leaf_nodes {
    private int countLeaves(Node root) {
        if (root == null ) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }
}
