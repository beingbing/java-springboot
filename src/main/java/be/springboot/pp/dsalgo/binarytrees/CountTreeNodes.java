package be.springboot.pp.dsalgo.binarytrees;

public class CountTreeNodes {
    private int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
