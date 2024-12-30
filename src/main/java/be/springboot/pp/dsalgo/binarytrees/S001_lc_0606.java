package be.springboot.pp.dsalgo.binarytrees;

public class S001_lc_0606 {
    public String tree2str(Node root) {
        if (root == null) return "";
        String result = String.valueOf(root.data); // Convert the root value to string
        if (root.left != null) result += "(" + tree2str(root.left) + ")"; // If the node has a left child, process it
        if (root.right != null) { // If the node has a right child, process it, and ensure parentheses for the left child if absent
            if (root.left == null) result += "()";
            result += "(" + tree2str(root.right) + ")";
        }
        return result;
    }
}
