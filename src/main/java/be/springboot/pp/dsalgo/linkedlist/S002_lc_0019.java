package be.springboot.pp.dsalgo.linkedlist;

public class S002_lc_0019 {
    public Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0, head);
        Node slow = dummy, fast = dummy;

        for (int i = 0; i <= n; i++) fast = fast.next;

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    // Example usage
    public static void main(String[] args) {
        S002_lc_0019 solution = new S002_lc_0019();

        // Example 1
        int[] data1 = {1, 2, 3, 4, 5};
        SinglyLinkedList list1 = new SinglyLinkedList(data1);
        list1.setHead(solution.removeNthFromEnd(list1.getHead(), 2));
        list1.printList(); // Output: [1, 2, 3, 5]

        // Example 2
        int[] data2 = {1};
        SinglyLinkedList list2 = new SinglyLinkedList(data2);
        list2.setHead(solution.removeNthFromEnd(list2.getHead(), 1));
        list2.printList(); // Output: []

        // Example 3
        int[] data3 = {1, 2};
        SinglyLinkedList list3 = new SinglyLinkedList(data3);
        list3.setHead(solution.removeNthFromEnd(list3.getHead(), 1));
        list3.printList(); // Output: [1]

        // Example 4
        int[] data4 = {1, 2};
        SinglyLinkedList list4 = new SinglyLinkedList(data4);
        list4.setHead(solution.removeNthFromEnd(list4.getHead(), 2));
        list4.printList(); // Output: [2]
    }
}
