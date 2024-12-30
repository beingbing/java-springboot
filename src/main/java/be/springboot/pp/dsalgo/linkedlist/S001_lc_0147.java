package be.springboot.pp.dsalgo.linkedlist;

public class S001_lc_0147 {
    public Node insertionSortList(Node head) {
        if (head == null || head.next == null) return head; // Already sorted

        // Dummy node to simplify insertion logic
        Node dummy = new Node(0);
        Node current = head; // Pointer for traversing the input list

        while (current != null) {
            Node nextNode = current.next; // Detach the current node from the list

            // Find the position to insert the current node in the sorted list
            Node prev = dummy;
            while (prev.next != null && prev.next.data < current.data) prev = prev.next;

            // Insert the current node into the sorted list
            current.next = prev.next;
            prev.next = current;

            // Move to the next node in the input list
            current = nextNode;
        }

        // Return the head of the sorted list
        return dummy.next;
    }
}
