package be.springboot.pp.dsalgo.linkedlist;

public class S006_lc_0082 {
    public Node deleteDuplicates(Node head) {
        Node dummy = new Node(0); // Dummy node to handle edge cases where head is part of duplicates
        dummy.next = head;

        Node prev = dummy; // Pointer to the last distinct node
        Node current = head; // Pointer to traverse the list

        while (current != null) {
            // Check if the current node is a duplicate
            if (current.next != null && current.data == current.next.data) {
                // Skip all nodes with the same value
                while (current.next != null && current.data == current.next.data) current = current.next;
                prev.next = current.next; // Link prev to the node after duplicates
            } else prev = prev.next; // Move prev only when current is distinct
            // Move current to the next node
            current = current.next;
        }

        return dummy.next; // Return the updated list starting from dummy.next
    }
}
