package be.springboot.pp.dsalgo.binarytrees;

public class S003_gfg_mirror_tree {
    public void mirror(Node root) {
        if (root == null) return;

        // Swap left and right children
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recur for left and right subtrees
        mirror(root.left);
        mirror(root.right);
    }
}
