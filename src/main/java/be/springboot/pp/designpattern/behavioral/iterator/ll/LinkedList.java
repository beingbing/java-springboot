package be.springboot.pp.designpattern.behavioral.iterator.ll;

public class LinkedList {
    private ListNode head;

    public void add(int value) {
        if (head == null) {
            head = new ListNode(value);
        } else {
            ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new ListNode(value);
        }
    }

    public LinkedListIterator iterator() {
        return new LinkedListIterator(head);
    }
}
