package be.springboot.pp.dsalgo.binarytrees;

public class S002_lc_0114 {
    public void flatten(Node root) {
        for (Node current = root; current != null; current = current.right) {
            if (current.left == null) continue;

            Node predecessor = current.left;
            while (predecessor.right != null) predecessor = predecessor.right;

            predecessor.right = current.right;
            current.right = current.left;
            current.left = null;
        }
    }
}

class S002_lc_0114_recursion_1 {
    public void flatten(Node root) {
        if (root == null) return;

        if (root.left != null) {
            Node predecessor = getRightmost(root.left);
            predecessor.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        flatten(root.right);
    }

    private Node getRightmost(Node node) {
        return node.right == null ? node : getRightmost(node.right);
    }
}

class S002_lc_0114_recursion_2 {
    public void flatten(Node root) {
        helper(root, null);
    }

    private Node helper(Node node, Node next) {
        if (node == null) return next;

        node.right = helper(node.right, next);
        node.right = helper(node.left, node.right);

        node.left = null;
        return node;
    }
}