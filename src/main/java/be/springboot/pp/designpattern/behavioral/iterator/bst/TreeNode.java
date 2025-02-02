package be.springboot.pp.designpattern.behavioral.iterator.bst;

// We have a Binary Search Tree (BST) that stores integers. We need an
// in-order iterator to traverse the BST without exposing the tree structure.
public class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}
