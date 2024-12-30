package be.springboot.pp.dsalgo.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

class DNode {
    public int val;
    public DNode prev;
    public DNode next;
    public DNode child;

    public DNode(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
        this.child = null;
    }
}

public class S001_lc_0430 {
    public DNode flatten(DNode head) {
        if (head == null) return null;

        // Stack to store nodes whose "next" pointers need to be processed later
        Deque<DNode> stack = new ArrayDeque<>();
        DNode current = head;

        while (current != null) {
            // If the current node has a child
            if (current.child != null) {
                // If there is a next node, push it onto the stack for later processing
                if (current.next != null) stack.push(current.next);

                // Connect the child list to the current node
                current.next = current.child;
                current.child.prev = current;
                current.child = null; // Set the child pointer to null
            }

            // If we reach the end of the current level and the stack is not empty
            if (current.next == null && !stack.isEmpty()) {
                DNode nextNode = stack.pop();
                current.next = nextNode;
                nextNode.prev = current;
            }

            // Move to the next node
            current = current.next;
        }

        return head;
    }
}
