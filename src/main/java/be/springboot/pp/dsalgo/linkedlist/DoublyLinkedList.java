package be.springboot.pp.dsalgo.linkedlist;

class DoublyLinkedList {
    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void insertAtPosition(int data, int position) {
        if (position < 0) throw new IllegalArgumentException("Position must be non-negative");
        Node newNode = new Node(data);
        if (position == 0) {
            insertAtBeginning(data);
            return;
        }
        Node temp = head;
        for (int i = 0; i < position - 1; i++) {
            if (temp == null) throw new IllegalArgumentException("Position out of bounds");
            temp = temp.next;
        }
        if (temp == tail) {
            insertAtEnd(data);
            return;
        }
        newNode.next = temp.next;
        newNode.prev = temp;
        if (temp.next != null) temp.next.prev = newNode;
        temp.next = newNode;
    }

    // Delete the first occurrence of a specific value
    public void deleteByValue(int value) {
        Node temp = head;
        while (temp != null && temp.data != value) {
            temp = temp.next;
        }
        if (temp == null) {
            return; // Value not found
        }
        if (temp == head) {
            deleteAtBeginning();
        } else if (temp == tail) {
            deleteAtEnd();
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    // Delete a node at the beginning
    public void deleteAtBeginning() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head == tail) { // Single element case
            head = tail = null;
            return;
        }
        head = head.next;
        head.prev = null;
    }

    // Delete a node at the end
    public void deleteAtEnd() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head == tail) { // Single element case
            head = tail = null;
            return;
        }
        tail = tail.prev;
        tail.next = null;
    }

    // Delete a node at a specific position (0-based index)
    public void deleteAtPosition(int position) {
        if (position < 0 || head == null) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        if (position == 0) {
            deleteAtBeginning();
            return;
        }
        Node temp = head;
        for (int i = 0; i < position; i++) {
            if (temp == null) {
                throw new IllegalArgumentException("Position out of bounds");
            }
            temp = temp.next;
        }
        if (temp == tail) {
            deleteAtEnd();
            return;
        }
        temp.prev.next = temp.next;
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
    }

    // Search for a value in the DoublyLinkedList
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

    // Print the DoublyLinkedList forward
    public void printListForward() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Print the DoublyLinkedList backward
    public void printListBackward() {
        Node temp = tail;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }

    // Get the size of the DoublyLinkedList
    public int size() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class Mainly {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        // Sample data
        int[] data = {10, 20, 30, 40, 50};
        for (int value : data) {
            list.insertAtEnd(value);
        }

        System.out.println("Original List (Forward):");
        list.printListForward();

        System.out.println("Original List (Backward):");
        list.printListBackward();

        list.insertAtBeginning(5);
        System.out.println("After inserting 5 at the beginning:");
        list.printListForward();

        list.insertAtPosition(25, 3);
        System.out.println("After inserting 25 at position 3:");
        list.printListForward();

        list.deleteByValue(40);
        System.out.println("After deleting value 40:");
        list.printListForward();

        list.deleteAtPosition(2);
        System.out.println("After deleting node at position 2:");
        list.printListForward();

        System.out.println("Reversed List (Backward):");
        list.printListBackward();

        System.out.println("Size of the list: " + list.size());

        System.out.println("Is value 30 in the list? " + list.search(30));
    }
}
