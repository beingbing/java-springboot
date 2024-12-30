package be.springboot.pp.dsalgo.linkedlist;

public class S002_lc_0002 {
    public Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0); // Dummy node for result list
        Node current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.data : 0; // Value from l1 or 0 if null
            int y = (l2 != null) ? l2.data : 0; // Value from l2 or 0 if null

            int sum = x + y + carry; // Sum current digits and carry
            carry = sum / 10;       // Compute carry for the next step
            current.next = new Node(sum % 10); // Add new digit to result
            current = current.next;

            if (l1 != null) l1 = l1.next; // Move to next node in l1
            if (l2 != null) l2 = l2.next; // Move to next node in l2
        }

        // If there's a carry left, add a new node
        if (carry > 0) current.next = new Node(carry);

        return dummy.next; // Return the result list (skip the dummy node)
    }
}
