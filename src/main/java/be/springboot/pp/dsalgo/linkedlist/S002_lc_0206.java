package be.springboot.pp.dsalgo.linkedlist;

public class S002_lc_0206 {
    public Node reverseRecursive(Node head) {
        // Base case: if head is null or only one node is present
        if (head == null || head.next == null) return head;

        Node newHead = reverseRecursive(head.next);

        // Reverse the current node's link
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
