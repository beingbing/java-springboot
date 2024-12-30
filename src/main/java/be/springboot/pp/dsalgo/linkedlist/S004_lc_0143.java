package be.springboot.pp.dsalgo.linkedlist;

public class S004_lc_0143 {
    public void reorderList(Node head) {
        if (head == null || head.next == null) return; // List is too short to reorder

        // Step 1: Find the middle of the list
        Node mid = middleNode(head);

        // Step 2: Reverse the second half of the list
        Node secondHalf = reverseList(mid.next);
        mid.next = null; // Break the list into two halves

        // Step 3: Merge the two halves
        Node firstHalf = head, temp1, temp2;
        while (secondHalf != null) {
            temp1 = firstHalf.next;
            temp2 = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = temp1;

            firstHalf = temp1;
            secondHalf = temp2;
        }
    }

    public Node middleNode(Node head) {
        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps
        }

        return slow;
    }

    private Node reverseList(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
