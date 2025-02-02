package be.springboot.pp.designpattern.behavioral.iterator.bst;

import be.springboot.pp.designpattern.behavioral.iterator.Iterable;
import be.springboot.pp.designpattern.behavioral.iterator.Iterator;

public class Tester {

    public static void main(String[] args) {
        // Create BST
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);

        Bst bst = new Bst(root);
        display(bst);
    }

    private static void display(Iterable iterable) {
        Iterator iterator = iterable.getIterator();
        System.out.println("In-Order Traversal using Iterator:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
