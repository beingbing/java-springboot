package be.springboot.pp.dsalgo.binarysearchtrees;

public class S002_lc_1008 {
    private int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return construct(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode construct(int[] preorder, int lower, int upper) {
        if (index >= preorder.length || preorder[index] < lower || preorder[index] > upper) return null;

        int value = preorder[index++];
        TreeNode root = new TreeNode(value);

        root.left = construct(preorder, lower, value);
        root.right = construct(preorder, value, upper);

        return root;
    }
}
