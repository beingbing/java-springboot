package be.springboot.pp.dsalgo.linkedlist;

public class S001_lc_0092 {
    public Node reverseBetween(Node head, int left, int right) {
        // Edge case: If the list has only one node or no reversal is needed
        if (head == null || left == right) return head;

        // Step 1: Dummy node to handle edge cases like reversing from the first node
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        // Step 2: Move prev to the node before the `left` position
        for (int i = 1; i < left; i++) prev = prev.next;

        // Step 3: Reverse the sublist between `left` and `right`
        Node curr = prev.next;
        Node next;
        for (int i = 0; i < right - left; i++) {
            next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        // Step 4: Return the modified list
        return dummy.next;
    }
}
