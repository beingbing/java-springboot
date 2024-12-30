package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class S005_lc_0971 {
    private int index = 0;
    private final List<Integer> flips = new ArrayList<>();

    public List<Integer> flipMatchVoyage(Node root, int[] voyage) {
        if (dfs(root, voyage)) return flips;
        else return List.of(-1); // Return [-1] if matching is impossible
    }

    private boolean dfs(Node node, int[] voyage) {
        if (node == null) return true; // Null node matches trivially
        if (node.data != voyage[index++]) return false;

        if (node.left != null && node.left.data != voyage[index]) { // Check if we need to flip
            flips.add(node.data); // Flip is needed, add current node to flips
            return dfs(node.right, voyage) && dfs(node.left, voyage); // Check right child first after flipping
        }
        return dfs(node.left, voyage) && dfs(node.right, voyage); // check root->left->right if no flip required
    }
}
