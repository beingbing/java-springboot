package be.springboot.pp.dsalgo.linkedlist;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Initialize dummy head and tail nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Get the value of the key if it exists, otherwise return -1
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    // Put a key-value pair in the cache
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);

            if (map.size() > capacity) {
                Node tailNode = removeTail();
                map.remove(tailNode.key);
            }
        }
    }

    // Move a node to the head of the doubly linked list
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // Add a node to the head of the doubly linked list
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Remove a node from the doubly linked list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Remove the tail node
    private Node removeTail() {
        Node tailNode = tail.prev;
        removeNode(tailNode);
        return tailNode;
    }
}

// Example Usage
class Main2 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);

        System.out.println(cache.get(1)); // Output: 100
        cache.put(4, 400); // Evicts key 2
        System.out.println(cache.get(2)); // Output: -1 (not found)

        cache.put(5, 500); // Evicts key 3
        System.out.println(cache.get(3)); // Output: -1 (not found)

        System.out.println(cache.get(4)); // Output: 400
        System.out.println(cache.get(5)); // Output: 500
    }
}
