package be.springboot.pp.dsalgo.linkedlist;

public class S001_lc_0021 {
    public Node mergeTwoLists(Node list1, Node list2) {
        Node dummy = new Node(-1); // Dummy node to simplify list construction
        Node tail = dummy;

        // Traverse both lists
        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next; // Move the tail forward
        }

        // Append remaining nodes from either list
        if (list1 != null) {
            tail.next = list1;
        } else if (list2 != null) {
            tail.next = list2;
        }

        return dummy.next; // Return merged list starting from dummy.next
    }
}

class recursive {
    public Node mergeTwoLists(Node list1, Node list2) {
        if (list1 == null) return list2; // Base case: list1 is empty
        if (list2 == null) return list1; // Base case: list2 is empty

        // Recursive case: compare and merge
        if (list1.data <= list2.data) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}