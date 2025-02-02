package be.springboot.pp.designpattern.behavioral.iterator.bst;

import be.springboot.pp.designpattern.behavioral.iterator.Iterable;
import be.springboot.pp.designpattern.behavioral.iterator.Iterator;

public class Bst implements Iterable {
    private final TreeNode root;

    public Bst(TreeNode node) {
        this.root = node;
    }

    @Override
    public Iterator getIterator() {
        return new BstIterator(root);
    }
}
