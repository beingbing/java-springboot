package be.springboot.pp.dsalgo.linkedlist;

public class S003_gfg_y_meeting {
    public int getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) return -1; // No intersection possible

        Node p1 = headA;
        Node p2 = headB;

        // Traverse both lists
        while (p1 != p2) {
            // If p1 reaches the end, redirect it to headB
            p1 = (p1 == null) ? headB : p1.next;

            // If p2 reaches the end, redirect it to headA
            p2 = (p2 == null) ? headA : p2.next;
        }

        // If there is an intersection, return the intersection node's value
        return (p1 != null) ? p1.data : -1;
    }
}

class S003_gfg_y_meeting_2 {
    public int getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) return -1; // No intersection possible

        // Step 1: Temporarily modify the first list to form a loop.
        Node tailA = headA;
        while (tailA.next != null) tailA = tailA.next; // Traverse to the last node of list A.
        tailA.next = headA; // Create a loop by linking the tail of list A to its head.

        // Step 2: Use Floyd’s Cycle Detection Algorithm on list B.
        Node slowPointer = headB; // Slow pointer starts at the head of list B.
        Node fastPointer = headB; // Fast pointer starts at the head of list B.

        boolean hasCycle = false;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next; // Slow pointer moves one step.
            fastPointer = fastPointer.next.next; // Fast pointer moves two steps.

            if (slowPointer == fastPointer) {
                hasCycle = true; // Cycle detected.
                break;
            }
        }

        // Step 3: If no cycle is detected, there is no intersection.
        if (!hasCycle) {
            tailA.next = null; // Restore the original structure of list A.
            return -1;
        }

        // Step 4: Find the intersection point.
        Node pointerB = headB; // Reset one pointer to the head of list B.
        while (pointerB != slowPointer) {
            pointerB = pointerB.next; // Both pointers move one step at a time.
            slowPointer = slowPointer.next;
        }

        // Step 5: Restore the original structure of list A before returning.
        tailA.next = null; // Remove the temporary loop.

        return pointerB.data; // Return the data at the intersection point.
    }
}
