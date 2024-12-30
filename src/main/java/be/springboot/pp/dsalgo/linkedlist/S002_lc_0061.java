package be.springboot.pp.dsalgo.linkedlist;

public class S002_lc_0061 {
    public Node rotateRight(Node head, int k) {
        // Edge case: If the list is empty or has only one node, or no rotation is needed
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Find the length of the linked list and make it circular
        Node curr = head;
        int length = 1;
        while (curr.next != null) {
            curr = curr.next;
            length++;
        }
        // Connect the last node to the head to make it circular
        curr.next = head;

        // Step 2: Compute the effective rotation
        k = k % length; // If k >= length, reduce it to within the bounds of the list
        int stepsToNewHead = length - k; // New head will be at this position

        // Step 3: Traverse to the new head and break the circle
        Node newTail = head;
        for (int i = 1; i < stepsToNewHead; i++) newTail = newTail.next;
        Node newHead = newTail.next;
        newTail.next = null; // Break the circle

        // Step 4: Return the new head
        return newHead;
    }
}
