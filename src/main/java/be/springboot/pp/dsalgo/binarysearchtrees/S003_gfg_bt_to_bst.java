package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class S003_gfg_bt_to_bst {
    public TreeNode binaryTreeToBST(TreeNode root) {
        if (root == null) return null;
        List<Integer> values = new ArrayList<>(); // Step 1: Perform inorder traversal to get all values
        inorderTraversal(root, values);
        Collections.sort(values); // Step 2: Sort the values
        Iterator<Integer> iterator = values.iterator(); // Step 3: Replace values during an inorder traversal
        convertToBST(root, iterator);
        return root;
    }

    private void inorderTraversal(TreeNode root, List<Integer> values) {
        if (root == null) return;
        inorderTraversal(root.left, values);
        values.add(root.data);
        inorderTraversal(root.right, values);
    }

    private void convertToBST(TreeNode root, Iterator<Integer> iterator) {
        if (root == null) return;
        convertToBST(root.left, iterator);
        root.data = iterator.next();
        convertToBST(root.right, iterator);
    }
}
