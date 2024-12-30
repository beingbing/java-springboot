package be.springboot.pp.dsalgo.linkedlist;

public class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Node(int data, Node node) {
        this.data = data;
        this.next = node;
        this.prev = null;
    }
}