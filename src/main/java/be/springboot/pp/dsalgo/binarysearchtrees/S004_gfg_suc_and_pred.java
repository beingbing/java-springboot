package be.springboot.pp.dsalgo.binarysearchtrees;

public class S004_gfg_suc_and_pred {
    public int findInorderSuccessor(TreeNode root, TreeNode k) {
        return findSuccessorRecursive(root, k, null);
    }

    private int findSuccessorRecursive(TreeNode root, TreeNode k, TreeNode successor) {
        if (root == null) return successor != null ? successor.data : -1;

        if (k.data < root.data) return findSuccessorRecursive(root.left, k, root); // Move left and update potential successor
        else return findSuccessorRecursive(root.right, k, successor); // Move right; successor won't change
    }

    public int findInorderSuccessorIterative(TreeNode root, TreeNode k) {
        TreeNode successor = null;

        while (root != null) { // Step 1: Traverse the tree to find k
            if (k.data < root.data) { // Current node could be a potential successor
                successor = root;
                root = root.left; // Move left to find smaller potential successor
            } else root = root.right; // Move right; successor won't be on the left
        }

        return successor != null ? successor.data : -1; // Step 2: Return the successor's value or -1 if no successor
    }
}
