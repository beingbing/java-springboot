package be.springboot.pp.dsalgo.linkedlist;

public class S002_gfg_quick_sort_ll {
    public Node quickSort(Node head) {
        if (head == null || head.next == null) return head; // Already sorted

        // Partition the list
        Node pivot = head;
        Node[] partitions = partition(head, pivot);

        // Recursively sort smaller and greater lists
        Node smallerSorted = quickSort(partitions[0]);
        Node greaterSorted = quickSort(partitions[2]);

        // Combine results
        return combine(smallerSorted, partitions[1], greaterSorted);
    }

    // Partition the list into smaller, equal, and greater lists
    private Node[] partition(Node head, Node pivot) {
        Node smallerHead = new Node(0), smallerTail = smallerHead;
        Node equalHead = new Node(0), equalTail = equalHead;
        Node greaterHead = new Node(0), greaterTail = greaterHead;

        while (head != null) {
            if (head.data < pivot.data) {
                smallerTail.next = head;
                smallerTail = smallerTail.next;
            } else if (head.data == pivot.data) {
                equalTail.next = head;
                equalTail = equalTail.next;
            } else {
                greaterTail.next = head;
                greaterTail = greaterTail.next;
            }
            head = head.next;
        }

        // Terminate all lists
        smallerTail.next = null;
        equalTail.next = null;
        greaterTail.next = null;

        return new Node[]{smallerHead.next, equalHead.next, greaterHead.next};
    }

    // Combine smaller, equal, and greater lists
    private Node combine(Node smaller, Node equal, Node greater) {
        Node dummy = new Node(0);
        Node current = dummy;

        // Append smaller list
        current.next = smaller;
        while (current.next != null) {
            current = current.next;
        }

        // Append equal list
        current.next = equal;
        while (current.next != null) {
            current = current.next;
        }

        // Append greater list
        current.next = greater;

        return dummy.next;
    }
}
