package be.springboot.pp.dsalgo.linkedlist;

public class S002_lc_0206 {
    public Node reverseList(Node head) {
        // Base case: if head is null or only one node is present
        if (head == null || head.next == null) return head;

        // Recursive call to reverse the rest of the list
        Node newHead = reverseList(head.next);

        // Reverse the current node's link
        head.next.next = head;
        head.next = null;

        // Return the new head of the reversed list
        return newHead;
    }

    public static void main(String[] args) {
        S002_lc_0206 solution = new S002_lc_0206();

        int[] a = {1, 2, 3, 4, 5};
        SinglyLinkedList list = new SinglyLinkedList(a);

        System.out.print("Original List: ");
        list.printList();

        list.setHead(solution.reverseList(list.getHead()));

        System.out.print("Reversed List: ");
        list.printList();
    }
}
