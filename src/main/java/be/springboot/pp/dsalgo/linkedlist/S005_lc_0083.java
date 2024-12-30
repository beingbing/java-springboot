package be.springboot.pp.dsalgo.linkedlist;

public class S005_lc_0083 {
    public Node deleteDuplicates(Node head) {
        if (head == null || head.next == null) return head; // If the list is empty or has only one node, no duplicates to remove

        Node current = head; // Pointer to traverse the list

        // Traverse the list while current and current.next are not null
        while (current != null && current.next != null) {
            if (current.data == current.next.data) current.next = current.next.next; // Skip the duplicate node
            else current = current.next; // Move to the next node if no duplicate
        }

        return head; // Return the updated head of the list
    }
}
