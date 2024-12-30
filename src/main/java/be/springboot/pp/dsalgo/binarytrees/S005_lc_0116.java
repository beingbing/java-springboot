package be.springboot.pp.dsalgo.binarytrees;

class T_Node {
    int val;
    T_Node left;
    T_Node right;
    T_Node next;

    T_Node(int val) {
        this.val = val;
    }
}

public class S005_lc_0116 {
    public T_Node connect(T_Node root) {
        if (root == null) return null;

        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}

class S005_lc_0116_iterative {
    public T_Node connect(T_Node root) {
        if (root == null) return null;
        T_Node levelStart = root; // Start at the root node
        while (levelStart.left != null) {
            T_Node current = levelStart;
            while (current != null) {
                current.left.next = current.right;
                if (current.next != null) current.right.next = current.next.left;
                current = current.next;
            }
            levelStart = levelStart.left;
        }
        return root;
    }
}
