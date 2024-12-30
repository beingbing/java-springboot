package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.ArrayList;
import java.util.List;

public class S002_gfg_bst_common_nodes {

    public ArrayList<Integer> findCommonNodes(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inOrderTraversal(root1, list1);
        inOrderTraversal(root2, list2);

        return findIntersection(list1, list2); // Step 2: Find common elements using two-pointer technique
    }

    private void inOrderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inOrderTraversal(node.left, list); // Traverse left subtree
        list.add(node.data);               // Visit current node
        inOrderTraversal(node.right, list); // Traverse right subtree
    }

    private ArrayList<Integer> findIntersection(List<Integer> list1, List<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).equals(list2.get(j))) {
                result.add(list1.get(i));
                i++;
                j++;
            } else if (list1.get(i) < list2.get(j)) i++;
            else j++;
        }
        return result;
    }

}
