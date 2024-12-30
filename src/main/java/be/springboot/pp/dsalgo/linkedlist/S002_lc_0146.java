package be.springboot.pp.dsalgo.linkedlist;

import java.util.HashMap;
import java.util.Map;

class LRUNode {
    int key, value;
    LRUNode prev, next;

    public LRUNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class S002_lc_0146 {
    private final int capacity;  // Maximum capacity of the cache
    private final Map<Integer, LRUNode> cache;  // HashMap for fast access
    private final LRUNode head, tail;  // Pointers to the head and tail of the doubly linked list

    public S002_lc_0146(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Dummy head and tail nodes to simplify list operations
        head = new LRUNode(0, 0);
        tail = new LRUNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Move a node to the front of the list (mark as most recently used)
    private void moveToFront(LRUNode node) {
        removeNode(node);
        addToFront(node);
    }

    // Remove a node from the doubly linked list
    private void removeNode(LRUNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Add a node to the front of the doubly linked list
    private void addToFront(LRUNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Remove the least recently used node (from the back of the list)
    private LRUNode removeLRUNode() {
        LRUNode lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    // Get the value associated with the key
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;  // Key not found
        }

        LRUNode node = cache.get(key);
        moveToFront(node);  // Mark node as most recently used
        return node.value;
    }

    // Put a key-value pair in the cache
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // If key already exists, update value and mark node as most recently used
            LRUNode node = cache.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            // If key does not exist, create a new node
            LRUNode newNode = new LRUNode(key, value);
            cache.put(key, newNode);
            addToFront(newNode);

            // If cache exceeds capacity, evict the least recently used node
            if (cache.size() > capacity) {
                LRUNode lruNode = removeLRUNode();
                cache.remove(lruNode.key);
            }
        }
    }
}
