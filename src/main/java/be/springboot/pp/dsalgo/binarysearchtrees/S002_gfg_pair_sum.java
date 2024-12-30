package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.Stack;

public class S002_gfg_pair_sum {

    public int isPairPresent(TreeNode root, int target) {
        if (root == null) return 0;

        Stack<TreeNode> leftStack = new Stack<>(); // smallest at top
        Stack<TreeNode> rightStack = new Stack<>(); // largest at top

        pushLeft(leftStack, root);
        pushRight(rightStack, root);

        while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
            TreeNode leftNode = leftStack.peek();
            TreeNode rightNode = rightStack.peek();

            if (leftNode == rightNode) break; // If the pointers meet, stop the search

            int sum = leftNode.data + rightNode.data;

            if (sum == target) return 1; // Pair found
            if (sum < target) { // Move the left pointer forward
                leftNode = leftStack.pop();
                pushLeft(leftStack, leftNode.right);
            } else { // Move the right pointer backward
                rightNode = rightStack.pop();
                pushRight(rightStack, rightNode.left);
            }
        }

        return 0; // No pair found
    }

    private void pushLeft(Stack<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    private void pushRight(Stack<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }

}
