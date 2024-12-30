package be.springboot.pp.dsalgo.linkedlist;

public class S005_lc_0025 {
    public Node reverseKGroup(Node head, int k) {
        // Step 1: Check if there are at least k nodes to reverse
        Node temp = head;
        int count = 0;
        while (temp != null && count < k) {
            temp = temp.next;
            count++;
        }

        // If less than k nodes, return the current head (no reversal)
        if (count < k) return head;

        // Step 2: Reverse the first k nodes
        Node prev = null, curr = head, next = null;
        for (int i = 0; i < k; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step 3: Recursively reverse the rest of the list
        if (curr != null) head.next = reverseKGroup(curr, k);

        // Step 4: Return the new head of the reversed group
        return prev;
    }
}
