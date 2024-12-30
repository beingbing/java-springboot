package be.springboot.pp.dsalgo.binarytrees;

class TreeNode {
    String data;
    TreeNode left;
    TreeNode right;

    TreeNode(String val) {
        this.data = val;
        left = null;
        right = null;
    }
}

public class S002_gfg_expression_tree {
    public int evaluateExpressionTree(TreeNode root) {
        if (root == null) return 0;

        int leftValue = evaluateExpressionTree(root.left);
        int rightValue = evaluateExpressionTree(root.right);

        return switch (root.data) {
            case "+" -> leftValue + rightValue;
            case "-" -> leftValue - rightValue;
            case "*" -> leftValue * rightValue;
            case "/" -> {
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield leftValue / rightValue;
            }
            default -> Integer.parseInt(root.data);
        };
    }
}
