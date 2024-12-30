package be.springboot.pp.dsalgo.binarysearchtrees;

public class S002_gfg_short_dist_bw_nodes {
    public int distanceBetweenTwoKeys(TreeNode root, int a, int b) {
        if (root == null) return 0;
        if (root.data > a && root.data > b)
            return distanceBetweenTwoKeys(root.left, a, b); // Both keys lie in left subtree
        if (root.data < a && root.data < b)
            return distanceBetweenTwoKeys(root.right, a, b); // Both keys lie in right subtree

        // if keys lie in different subtree then current node is LCA. The
        // sum of distance of keys from current node is the answer.
        return distanceFromRoot(root, a) + distanceFromRoot(root, b);
    }

    private int distanceFromRoot(TreeNode root, int x) {
        if (root.data == x) return 0;
        else if (root.data > x) return 1 + distanceFromRoot(root.left, x);
        return 1 + distanceFromRoot(root.right, x);
    }
}
