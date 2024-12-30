package be.springboot.pp.dsalgo.linkedlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SinglyLinkedList {
    private Node head, tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public SinglyLinkedList(int[] a) {
        this.head = null;
        this.tail = null;
        for (int ele : a) {
            Node tmp = new Node(ele);
            if (head == null) {
                head = tmp;
                tail = tmp;
            } else {
                tail.next = tmp;
                tail = tail.next;
            }
        }
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = tail.next;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Insert at a specific position (0-based index)
    public void insertAtPosition(int data, int position) {
        if (position < 0) throw new IllegalArgumentException("Position must be non-negative");
        Node newNode = new Node(data);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node temp = head;
        for (int i = 0; i < position - 1; i++) {
            if (temp == null) throw new IllegalArgumentException("Position out of bounds");
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Delete the first occurrence of a specific value
    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }
        if (head.data == value) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.data != value) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Delete a node at a specific position (0-based index)
    public void deleteAtPosition(int position) {
        if (position < 0 || head == null) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        if (position == 0) {
            head = head.next;
            return;
        }
        Node temp = head;
        for (int i = 0; i < position - 1; i++) {
            if (temp.next == null) {
                throw new IllegalArgumentException("Position out of bounds");
            }
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Search for a value in the LinkedList
    public boolean search(int value) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Print the LinkedList
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Get the size of the LinkedList
    public int size() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Reverse the LinkedList
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
}

// Example usage
class Main {
    public static void main(String[] args) {
//        SinglyLinkedList list = new SinglyLinkedList();

        // Sample data
        int[] data = {10, 20, 30, 40, 50};
//        for (int value : data) {
//            list.insertAtEnd(value);
//        }

        SinglyLinkedList list = new SinglyLinkedList(data);

        System.out.println("Original List:");
        list.printList();

        list.insertAtBeginning(5);
        System.out.println("After inserting 5 at the beginning:");
        list.printList();

        list.insertAtPosition(25, 3);
        System.out.println("After inserting 25 at position 3:");
        list.printList();

        list.deleteByValue(40);
        System.out.println("After deleting value 40:");
        list.printList();

        list.deleteAtPosition(2);
        System.out.println("After deleting node at position 2:");
        list.printList();

        System.out.println("Reversed List:");
        list.reverse();
        list.printList();

        System.out.println("Size of the list: " + list.size());

        System.out.println("Is value 30 in the list? " + list.search(30));
    }
}
