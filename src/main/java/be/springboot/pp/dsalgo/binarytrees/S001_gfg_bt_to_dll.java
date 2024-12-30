package be.springboot.pp.dsalgo.binarytrees;

public class S001_gfg_bt_to_dll {
    private Node head = null;  // Head of the DLL
    private Node prev = null; // Tracks the previous node in DLL

    public Node bToDLL(Node node) {
        if (node == null) return null; // Base case: empty tree
        // perform in-order traversal
        bToDLL(node.left); // Process left subtree
        if (prev == null) head = node; // we're processing the first node of DLL, Set head of DLL
        else { // Link the current node with the previous node
            node.left = prev;
            prev.right = node;
        }
        prev = node; // Update prev to current node
        bToDLL(node.right); // Process right subtree
        return head; // Return the head of the DLL
    }
}

class S001_gfg_bt_to_dll_2 {

    private HeadTailPair convertToDLL(Node node) {
        if (node == null) return new HeadTailPair(null, null); // Base case: If the current subtree is empty, return null pointers

        HeadTailPair leftSubtree = convertToDLL(node.left);
        HeadTailPair rightSubtree = convertToDLL(node.right);

        Node head = node;
        Node tail = node;

        if (leftSubtree.tail != null) { // Connect the tail of the left subtree to the current root
            leftSubtree.tail.right = node; // Link the right of left subtree's tail to root
            node.left = leftSubtree.tail; // Link the left of root to left subtree's tail
            head = leftSubtree.head;      // Update head to the head of the left subtree
        }

        if (rightSubtree.head != null) { // Connect the root to the head of the right subtree
            rightSubtree.head.left = node; // Link the left of right subtree's head to root
            node.right = rightSubtree.head; // Link the right of root to right subtree's head
            tail = rightSubtree.tail;      // Update tail to the tail of the right subtree
        }

        // Return the head and tail of the DLL for the current subtree
        return new HeadTailPair(head, tail);
    }

    public Node bToDLL(Node root) {
        if (root == null) return null; // Handle empty tree case
        return convertToDLL(root).head; // Only the head of the DLL is needed
    }
}

class HeadTailPair {
    Node head;
    Node tail;

    HeadTailPair(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }
}
