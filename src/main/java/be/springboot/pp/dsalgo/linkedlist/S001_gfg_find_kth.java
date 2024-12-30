package be.springboot.pp.dsalgo.linkedlist;

public class S001_gfg_find_kth {
    public int findNKthElement(Node head, int k) {
        if (head == null) return -1;

        // Step 1: Find the length of the linked list
        int n = 0;
        Node current = head;
        while (current != null) {
            n++;
            current = current.next;
        }

        // Step 2: Calculate the target index (1-based)
        int targetIndex = (int) Math.ceil(n / (double) k);

        // Step 3: Traverse to the target node
        current = head;
        for (int i = 1; i < targetIndex; i++) current = current.next;

        // Step 4: Return the value of the target node
        return current.data;
    }
}
