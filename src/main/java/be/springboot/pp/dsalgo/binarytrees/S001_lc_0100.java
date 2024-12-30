package be.springboot.pp.dsalgo.binarytrees;

public class S001_lc_0100 {
    public boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) return true; // Both nodes are null
        if (p == null || q == null) return false; // One of the nodes is null
        if (p.data != q.data) return false; // Values are different
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
