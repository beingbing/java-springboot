package be.springboot.pp.dsalgo.binarytrees;

public class S003_gfg_ternary_binary_tree {
    public TreeNode buildTernaryExpressionTree(String expression, int index) {
        TreeNode node = new TreeNode(Character.toString(expression.charAt(index)));
        if (index == expression.length() - 1) return node;
        if (expression.charAt(index) == '?') node.left = buildTernaryExpressionTree(expression, index + 1);
        if (expression.charAt(index) == ':') node.right = buildTernaryExpressionTree(expression, index + 1);
        return node;
    }
}
