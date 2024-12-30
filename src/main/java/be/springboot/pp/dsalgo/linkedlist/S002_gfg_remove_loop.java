package be.springboot.pp.dsalgo.linkedlist;

public class S002_gfg_remove_loop {
    public boolean removeLoop(Node head) {
        if (head == null || head.next == null) return true; // No loop possible

        // Step 1: Detect the loop using Floyd's Cycle Detection Algorithm
        Node slow = head, fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow one step
            fast = fast.next.next; // Move fast two steps

            if (slow == fast) { // Loop detected
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return true; // No loop to remove

        // Step 2: Find the start of the loop
        slow = head;
        if (slow == fast) {
            // Special case: loop starts at the head
            while (fast.next != slow) {
                fast = fast.next;
            }
        } else {
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        // Step 3: Break the loop
        fast.next = null;

        return true; // Loop successfully removed
    }
}
