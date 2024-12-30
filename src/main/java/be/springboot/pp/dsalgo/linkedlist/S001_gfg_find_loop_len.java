package be.springboot.pp.dsalgo.linkedlist;

public class S001_gfg_find_loop_len {
    public int countNodesInLoop(Node head) {
        if (head == null || head.next == null) return 0; // No loop possible

        // Step 1: Detect if a loop exists using Floyd's Cycle Detection
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps

            if (slow == fast) return countLoopLength(slow); // Cycle detected
        }

        return 0; // No cycle found
    }

    private int countLoopLength(Node meetingPoint) {
        Node current = meetingPoint;
        int count = 1; // Start with the meeting node
        while (current.next != meetingPoint) {
            current = current.next;
            count++;
        }
        return count; // Total nodes in the loop
    }
}
