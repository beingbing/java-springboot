package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class S003_lc_0257 {
    public List<String> binaryTreePaths(Node root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result; // Edge case: empty tree
        dfs(root, "", result);
        return result;
    }

    private void dfs(Node node, String path, List<String> result) {
        // Append current node's value to the path
        path += node.data;

        // If it's a leaf node, add the path to the result
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        // If not a leaf, continue the path and explore children
        if (node.left != null) dfs(node.left, path + "->", result);
        if (node.right != null) dfs(node.right, path + "->", result);
    }
}
