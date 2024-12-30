package be.springboot.pp.dsalgo.binarytrees;

public class S003_gfg_child_sum_parent {
    public boolean isChildrenSum(Node root) {
        if (root == null || (root.left == null && root.right == null)) return true; // Base case: If the node is null or a leaf, it satisfies the property
        int leftValue = (root.left != null) ? root.left.data : 0;
        int rightValue = (root.right != null) ? root.right.data : 0;
        boolean currentNodeSatisfies = (root.data == leftValue + rightValue);
        boolean leftSubtreeSatisfies = isChildrenSum(root.left);
        boolean rightSubtreeSatisfies = isChildrenSum(root.right);
        return currentNodeSatisfies && leftSubtreeSatisfies && rightSubtreeSatisfies;
    }
}
