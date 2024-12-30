package be.springboot.pp.dsalgo.binarysearchtrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class S003_lc_1305 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        // Initialize stacks with the leftmost nodes of both trees
        pushLeftNodes(root1, stack1);
        pushLeftNodes(root2, stack2);

        // Merge elements using in-order traversal
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            Stack<TreeNode> currentStack =
                    stack1.isEmpty() ? stack2
                            : (stack2.isEmpty() ? stack1
                                : (stack1.peek().data <= stack2.peek().data ? stack1 : stack2));

            TreeNode currentNode = currentStack.pop();
            result.add(currentNode.data);
            pushLeftNodes(currentNode.right, currentStack);
        }

        return result;
    }

    private void pushLeftNodes(TreeNode node, Stack<TreeNode> stack) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
