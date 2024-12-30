package be.springboot.pp.dsalgo.linkedlist;

public class S003_lc_0024 {
    public Node swapPairs(Node head) {
        // Base case: If the list has 0 or 1 nodes, return as it is
        if (head == null || head.next == null) return head;

        // Initialize pointers
        Node dummy = new Node(0); // Dummy node to simplify edge cases
        dummy.next = head;
        Node prev = dummy; // node pointing to the first node of pair
        Node curr = head; // first node of the pair

        // Traverse the list and swap pairs
        while (curr != null && curr.next != null) {
            Node first = curr;        // First node of the pair
            Node second = curr.next; // Second node of the pair

            // Swapping
            first.next = second.next; // Point first to the node after second
            second.next = first;     // Point second to first
            prev.next = second;      // Point prev to second

            // Move pointers forward
            prev = first;
            curr = first.next;
        }

        // Return the new head of the list
        return dummy.next;
    }
}
