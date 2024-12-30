package be.springboot.pp.dsalgo.linkedlist;

public class S002_lc_1669 {
    public Node mergeInBetween(Node list1, int a, int b, Node list2) {
        // Step 1: Find the node before index `a` (prev_a) and the node after index `b` (next_b)
        Node prev_a = list1;
        for (int i = 1; i < a; i++) prev_a = prev_a.next;

        Node next_b = list1;
        for (int i = 0; i <= b; i++) next_b = next_b.next;

        // Step 2: Connect prev_a to the head of list2
        prev_a.next = list2;

        // Step 3: Connect the last node of list2 to next_b
        Node current = list2;
        while (current.next != null) current = current.next;
        current.next = next_b;

        // Step 4: Return the modified list1
        return list1;
    }

    // Example usage
    public static void main(String[] args) {
        S002_lc_1669 solution = new S002_lc_1669();

        // Example 1
        int[] a = {10, 1, 13, 6, 9, 5};
        SinglyLinkedList list1 = new SinglyLinkedList(a);

        int[] b = {1000000,1000001,1000002};
        SinglyLinkedList list2 = new SinglyLinkedList(b);

        Node result = solution.mergeInBetween(list1.getHead(), 3, 4, list2.getHead());
        list1.printList(); // printList(result); // Output: [10, 1, 13, 1000000, 1000001, 1000002, 5]

        // Example 2
        int[] c = {0,1,2,3,4,5,6};
        SinglyLinkedList list3 = new SinglyLinkedList(c);

        int[] d = {1000000,1000001,1000002,1000003,1000004};
        SinglyLinkedList list4 = new SinglyLinkedList(d);

        result = solution.mergeInBetween(list3.getHead(), 2, 5, list4.getHead());
        list3.printList(); // printList(result); // Output: [0, 1, 1000000, 1000001, 1000002, 1000003, 1000004, 6]
    }
}
