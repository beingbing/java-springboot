package be.springboot.pp.dsalgo.linkedlist;

class DeepNode {
    int data;
    DeepNode next;
    DeepNode bottom;
}


public class S004_gfg_flatten_ll {
    public DeepNode flatten(DeepNode root) {
        if (root == null || root.next == null) return root; // Base case: if root is null or only one list exists

        root.next = flatten(root.next); // Recursively flatten the next list
        root = mergeTwoLists(root, root.next); // Merge the current list with the flattened next list
        return root; // Return the merged list
    }

    // Helper function to merge two sorted linked lists
    private DeepNode mergeTwoLists(DeepNode a, DeepNode b) {
        if (a == null) return b;
        if (b == null) return a;

        DeepNode result;

        if (a.data <= b.data) {
            result = a;
            result.bottom = mergeTwoLists(a.bottom, b);
        } else {
            result = b;
            result.bottom = mergeTwoLists(a, b.bottom);
        }

        return result;
    }
}
