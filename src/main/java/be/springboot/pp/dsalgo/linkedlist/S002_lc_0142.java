package be.springboot.pp.dsalgo.linkedlist;

public class S002_lc_0142 {
    public Node detectCycle(Node head) {
        if (head == null || head.next == null) return null; // No cycle possible

        Node slow = head;
        Node fast = head;

        // Step 1: Detect if there is a cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow pointer one step
            fast = fast.next.next;    // Move fast pointer two steps
            if (slow == fast) break; // Cycle detected
        }

        // If no cycle, return null
        if (fast == null || fast.next == null) return null;

        // Step 2: Find the start of the cycle
        // (l1 + d) % l2 = 0
        // l1: length of straight part
        // d: distance from start of circular part to where the pointers meet
        // l2: length of circular part
        // once they met, if we reset slow to head and move both of them one unit at a time
        // fast will complete l2 - d and slow will complete l1, and there meeting point will
        // be starting head of the cycle.
        slow = head;                  // Reset slow to head
        while (slow != fast) {
            slow = slow.next;         // Move both one step at a time
            fast = fast.next;
        }

        return slow; // Cycle starts here
    }
}
