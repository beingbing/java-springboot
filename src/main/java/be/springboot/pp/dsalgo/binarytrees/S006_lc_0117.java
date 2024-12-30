package be.springboot.pp.dsalgo.binarytrees;

public class S006_lc_0117 {
    public T_Node connect(T_Node root) {
        if (root == null) return null;

        T_Node nextNode = root.next;
        while (nextNode != null) {
            if (nextNode.left != null) {
                nextNode = nextNode.left;
                break;
            }
            if (nextNode.right != null) {
                nextNode = nextNode.right;
                break;
            }
            nextNode = nextNode.next;
        }

        if (root.left != null) root.left.next = root.right != null ? root.right : nextNode;
        if (root.right != null) root.right.next = nextNode;

        connect(root.right);
        connect(root.left);

        return root;
    }
}

class S006_lc_0117_iterative {
    public T_Node connect(T_Node root) {
        if (root == null) return null;

        T_Node current = root; // Start with the root
        while (current != null) {
            T_Node dummy = new T_Node(0); // Dummy node to track the next level
            T_Node temp = dummy; // Temp pointer to construct connections

            while (current != null) {
                if (current.left != null) {
                    temp.next = current.left;
                    temp = temp.next;
                }
                if (current.right != null) {
                    temp.next = current.right;
                    temp = temp.next;
                }
                current = current.next; // Move to the next node at the same level
            }

            current = dummy.next;
        }

        return root;
    }
}
