package be.springboot.pp.dsalgo.binarysearchtrees;

public class S003_gfg_bst_from_postorder {
    private int index;

    public TreeNode constructTree(int post[], int n) {
        index = n - 1; // Start from the last index of postorder array
        return build(post, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] post, int lower, int upper) {
        if (index < 0 || post[index] < lower || post[index] > upper) return null;

        int value = post[index--];
        TreeNode root = new TreeNode(value);

        root.right = build(post, value, upper); // Right subtree
        root.left = build(post, lower, value);  // Left subtree

        return root;
    }
}
