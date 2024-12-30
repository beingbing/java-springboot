package be.springboot.pp.dsalgo.binarytrees;

public class S004_gfg_bt_to_cdll {
    private Node head = null; // To track the head of the CDLL
    private Node prev = null; // To track the previous node during traversal

    public Node bTreeToCList(Node root) {
        if (root == null) return null;
        inorderTraversal(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    private void inorderTraversal(Node node) {
        if (node == null) return;
        inorderTraversal(node.left);
        if (prev == null) head = node; // First node (head of the CDLL)
        else {
            // Link the current node with the previous node
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        inorderTraversal(node.right);
    }
}

class S004_gfg_bt_to_cdll_2 {
    private HeadTailPair convertToCDLL(Node root) {
        if (root == null) return new HeadTailPair(null, null);
        HeadTailPair leftSubtree = convertToCDLL(root.left);
        HeadTailPair rightSubtree = convertToCDLL(root.right);

        Node head = root;
        Node tail = root;

        if (leftSubtree.tail != null) { // Connect the tail of the left subtree to the current node
            leftSubtree.tail.right = root; // Right of the last node in left subtree points to root
            root.left = leftSubtree.tail; // Left of root points to the last node in left subtree
            head = leftSubtree.head;      // Update head to the head of the left subtree
        }

        if (rightSubtree.head != null) { // Connect the current node to the head of the right subtree
            rightSubtree.head.left = root; // Left of the first node in right subtree points to root
            root.right = rightSubtree.head; // Right of root points to the first node in right subtree
            tail = rightSubtree.tail;      // Update tail to the tail of the right subtree
        }

        return new HeadTailPair(head, tail);
    }

    public Node bTreeToCList(Node root) {
        if (root == null) return null; // Handle the case for an empty tree

        HeadTailPair result = convertToCDLL(root);
        Node head = result.head;
        Node tail = result.tail;

        if (head != null && tail != null) { // Make the doubly linked list circular by connecting head and tail
            head.left = tail;  // Left of head points to tail
            tail.right = head; // Right of tail points to head
        }

        return head; // Return the head of the circular doubly linked list
    }
}
