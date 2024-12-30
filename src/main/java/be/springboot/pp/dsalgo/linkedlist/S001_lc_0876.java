package be.springboot.pp.dsalgo.linkedlist;

public class S001_lc_0876 {
    public Node middleNode(Node head) {
        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps
        }

        return slow;
    }
}
