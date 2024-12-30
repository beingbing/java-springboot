package be.springboot.pp.dsalgo.linkedlist;

public class S002_gfg_segregate_nodes {
    public Node segregateEvenOdd(Node head) {
        if (head == null || head.next == null) {
            return head; // No rearrangement needed for empty or single-node list
        }

        // Dummy nodes to start even and odd lists
        Node evenHead = new Node(0);
        Node oddHead = new Node(0);

        // Tail pointers for even and odd lists
        Node evenTail = evenHead;
        Node oddTail = oddHead;

        Node current = head;

        while (current != null) {
            if (current.data % 2 == 0) {
                // Add to even list
                evenTail.next = current;
                evenTail = evenTail.next;
            } else {
                // Add to odd list
                oddTail.next = current;
                oddTail = oddTail.next;
            }
            current = current.next;
        }

        // Connect even and odd lists
        evenTail.next = oddHead.next;
        oddTail.next = null; // Terminate the list

        return evenHead.next; // The real head of the even list
    }
}
