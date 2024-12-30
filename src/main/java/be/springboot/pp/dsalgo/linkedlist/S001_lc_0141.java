package be.springboot.pp.dsalgo.linkedlist;

public class S001_lc_0141 {
    public boolean hasCycle(Node head) {
        if (head == null || head.next == null) return false; // No cycle possible in empty or single-node list

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow pointer one step
            fast = fast.next.next;    // Move fast pointer two steps

            if (slow == fast) {       // Cycle detected
                return true;
            }
        }

        return false; // If fast pointer reaches the end, no cycle exists
    }
}
