package be.springboot.pp.designpattern.behavioral.iterator.ll;

import be.springboot.pp.designpattern.behavioral.iterator.Iterator;
import be.springboot.pp.dsalgo.linkedlist.Node;

public class LinkedListIterator implements Iterator {
    private ListNode current;

    public LinkedListIterator(ListNode head) {
        this.current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public int next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        int value = current.value;
        current = current.next; // Move to next node
        return value;
    }
}
