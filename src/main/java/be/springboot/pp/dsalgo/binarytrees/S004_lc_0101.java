package be.springboot.pp.dsalgo.binarytrees;

public class S004_lc_0101 {
    public boolean isSymmetric(Node root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(Node left, Node right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.data == right.data && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
