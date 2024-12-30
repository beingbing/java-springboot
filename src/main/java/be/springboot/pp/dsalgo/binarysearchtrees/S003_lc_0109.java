package be.springboot.pp.dsalgo.binarysearchtrees;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class S003_lc_0109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null; // Edge case: empty list
        return constructTree(head, null);
    }

    private TreeNode constructTree(ListNode head, ListNode tail) {
        if (head == tail) return null; // Base case: no nodes in the range

        // Step 1: Find the middle node of the current sublist
        ListNode slow = head;
        ListNode fast = head;

        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Use the middle node as the root of the current subtree
        TreeNode root = new TreeNode(slow.val);

        // Step 3: Recursively construct the left and right subtrees
        root.left = constructTree(head, slow); // Left subtree with nodes before the middle
        root.right = constructTree(slow.next, tail); // Right subtree with nodes after the middle

        return root;
    }
}
