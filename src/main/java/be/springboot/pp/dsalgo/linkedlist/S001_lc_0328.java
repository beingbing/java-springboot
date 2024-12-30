package be.springboot.pp.dsalgo.linkedlist;

public class S001_lc_0328 {
    public Node oddEvenList(Node head) {
        if (head == null || head.next == null) {
            return head; // No reordering needed for empty or single-node list
        }

        // Initialize odd and even pointers
        Node odd = head;
        Node even = head.next;
        Node evenHead = even; // To connect the last odd node to the first even node

        while (even != null && even.next != null) {
            // Connect odd nodes
            odd.next = even.next;
            odd = odd.next;

            // Connect even nodes
            even.next = odd.next;
            even = even.next;
        }

        // Connect odd list to even list
        odd.next = evenHead;

        return head;
    }
}
