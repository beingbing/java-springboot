package be.springboot.pp.dsalgo.linkedlist;

public class S004_gfg_ll_subtract {
    public Node subtractLinkedLists(Node head1, Node head2) {
        // Step 1: Determine which list represents the larger number
        if (isSmaller(head1, head2)) {
            Node temp = head1;
            head1 = head2;
            head2 = temp;
        }

        // Step 2: Reverse both lists to simplify subtraction
        head1 = reverseList(head1);
        head2 = reverseList(head2);

        // Step 3: Perform digit-by-digit subtraction
        Node result = subtractLists(head1, head2);

        // Step 4: Reverse the result list back to original order
        result = reverseList(result);

        // Step 5: Remove leading zeros
        result = removeLeadingZeros(result);

        return result;
    }

    // Helper function to check if list1 is smaller than list2
    private boolean isSmaller(Node head1, Node head2) {
        int len1 = getLength(head1);
        int len2 = getLength(head2);

        if (len1 != len2) return len1 < len2;

        while (head1 != null && head2 != null) {
            if (head1.data != head2.data)
                return head1.data < head2.data;
            head1 = head1.next;
            head2 = head2.next;
        }
        return false; // Equal lists
    }

    // Function to get the length of a linked list
    private int getLength(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    // Function to reverse a linked list
    private Node reverseList(Node head) {
        Node prev = null, current = head, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // Function to perform subtraction of two reversed lists
    private Node subtractLists(Node head1, Node head2) {
        Node dummy = new Node(0);
        Node current = dummy;
        int borrow = 0;

        while (head1 != null) {
            int digit1 = head1.data;
            int digit2 = (head2 != null) ? head2.data : 0;

            int diff = digit1 - digit2 - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            current.next = new Node(diff);
            current = current.next;

            head1 = head1.next;
            if (head2 != null) head2 = head2.next;
        }

        return dummy.next;
    }

    // Function to remove leading zeros
    private Node removeLeadingZeros(Node head) {
        while (head != null && head.data == 0) {
            head = head.next;
        }
        return (head == null) ? new Node(0) : head;
    }

    // Function to print a linked list (for debugging)
    public void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    // Example usage
    public static void main(String[] args) {
        S004_gfg_ll_subtract subtractor = new S004_gfg_ll_subtract();

        Node head1 = new Node(1);
        head1.next = new Node(0);
        head1.next.next = new Node(0);

        Node head2 = new Node(1);
        head2.next = new Node(2);

        Node result = subtractor.subtractLinkedLists(head1, head2);
        subtractor.printList(result); // Output: 8 -> 8 -> null

        // Example 2
        Node l3 = new Node(6);
        l3.next = new Node(3);

        Node l4 = new Node(7);
        l4.next = new Node(1);
        l4.next.next = new Node(0);

        result = subtractor.subtractLinkedLists(l3, l4);
        subtractor.printList(result); // Output: 6->4->7
    }
}
