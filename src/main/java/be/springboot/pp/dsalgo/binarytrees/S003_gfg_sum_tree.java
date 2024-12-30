package be.springboot.pp.dsalgo.binarytrees;

public class S003_gfg_sum_tree {
    public void toSumTree(Node root) {
        transformToSumTree(root);
    }

    private int transformToSumTree(Node node) {
        if (node == null) return 0; // if node has no child, set it to 0
        int leftSum = transformToSumTree(node.left);
        int rightSum = transformToSumTree(node.right);
        int originalValue = node.data;
        node.data = leftSum + rightSum;
        return originalValue + node.data;
    }
}
