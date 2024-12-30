package be.springboot.pp.dsalgo.binarytrees;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        next = null;
    }
}

public class S001_lc_1367 {
    public boolean isSubPath(ListNode head, Node root) {
        if (head == null) return true;
        if (root == null) return false;
        return dfs(head, root) // Check if the current node is the start of a valid path
                || isSubPath(head, root.left)
                || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, Node root) {
        if (head == null) return true; // if a linked-list is fully traversed, then a path is found
        if (root == null || root.data != head.val) return false; // if tree ends before, or value do not match
        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }
}
