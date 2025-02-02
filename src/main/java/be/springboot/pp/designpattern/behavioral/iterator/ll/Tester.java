package be.springboot.pp.designpattern.behavioral.iterator.ll;

public class Tester {

    public static void main(String[] args) {
        // Create Linked List
        LinkedList list = new LinkedList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        // Use Linked List Iterator
        LinkedListIterator iterator = list.iterator();
        System.out.println("Linked List Traversal using Iterator:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
